package com.lyun.estate.biz.company.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.base.Strings;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.CompanyDTO;
import com.lyun.estate.biz.company.domain.CompanySigningDTO;
import com.lyun.estate.biz.company.domain.CreateCompanyInfo;
import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.repo.CompanyRepository;
import com.lyun.estate.biz.company.support.PositionPermissionTemplate;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.permission.service.GrantService;
import com.lyun.estate.biz.position.def.PositionType;
import com.lyun.estate.biz.position.entity.Position;
import com.lyun.estate.biz.position.service.PositionService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyService {

    private final String defaultPassword = "ykdc1234";

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private GrantService grantService;
    @Autowired
    private CompanySigningService companySigningService;

    @Transactional
    public Company createCompany(CreateCompanyInfo createCompanyInfo, Long operatorId) {
        ExceptionUtil.checkNotNull("加盟信息", createCompanyInfo);
        ExceptionUtil.checkNotNull("城市信息", createCompanyInfo.getCityId());
        ExceptionUtil.checkNotNull("加盟公司", createCompanyInfo.getParentId());
        ExceptionUtil.checkNotNull("加盟负责人", createCompanyInfo.getPartAId());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(createCompanyInfo.getName()),
                "公司名", createCompanyInfo.getName());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(createCompanyInfo.getAbbr()),
                "公司简称", createCompanyInfo.getAbbr());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(createCompanyInfo.getBossName()),
                "公司负责人", createCompanyInfo.getBossName());
        ExceptionUtil.checkIllegal(ValidateUtil.isMobile(createCompanyInfo.getMobile()),
                "公司负责人手机",
                createCompanyInfo.getMobile());

        checkCompanyParent(createCompanyInfo.getParentId(), createCompanyInfo.getType());

        //1. create company
        Company company = createCompanyInfo.getCompanyINfo();
        String secretKey = assignSecretKey(company.getAbbr());
        company.setSecretKey(secretKey);

        repository.insert(company);
        //2. create dept
        Department headquarters = departmentService.create(new Department()
                .setCityId(company.getCityId())
                .setAddress(company.getAddress())
                .setCompanyId(company.getId())
                .setName(company.getAbbr())
                .setParentId(0L)
        );

        //3. create position
        Position manager = createPosition(company.getId(), company.getType(), PositionType.REGION_M, operatorId);

        createPosition(company.getId(), company.getType(), PositionType.DEPT_M, operatorId);

        createPosition(company.getId(), company.getType(), PositionType.BUSINESS, operatorId);

        //4. create employee
        String salt = CommonUtil.getUuid().replace("-", "");
        String password = CommonUtil.hmac(salt, defaultPassword);

        Employee boss = employeeService.create(
                createCompanyInfo.getBossInfo()
                        .setCompanyId(company.getId())
                        .setDepartmentId(headquarters.getId())
                        .setPositionId(manager.getId())
                        .setSalt(salt)
                        .setPassword(password)
        );

        //5. grant employee permission
        grantService.regrantEmployeeByPosition(boss.getId(), manager.getId(), operatorId);

        //7. create signing
        companySigningService.create(createCompanyInfo
                .getSigningInfo()
                .setCompanyId(company.getId()));

        //8. update bossId
        repository.updateBossId(company.getId(), boss.getId());

        return repository.findOne(company.getId());
    }

    private void checkCompanyParent(Long parentId, CompanyDefine.Type type) {
        Company parent = repository.findOne(parentId);
        if (parent == null || !parent.getType().createTypes().contains(type)) {
            throw new EstateException(ExCode.COMPANY_CREATE_PARENT_ERROR);
        }
    }

    private Position createPosition(Long companyId, CompanyDefine.Type companyType,
                                    PositionType positionType, Long operatorId) {
        Position position = positionService.create(new Position()
                .setCompanyId(companyId)
                .setName(positionType.getLabel())
                .setType(positionType)
        );

        //grant position permission
        Map<PermissionDefine.Category, List<Grant>> categoryGrantsMap = PositionPermissionTemplate.defaultPermissions(
                companyType, positionType);
        for (PermissionDefine.Category category : categoryGrantsMap.keySet()) {
            grantService.regrant(position.getId(),
                    DomainType.POSITION,
                    category,
                    categoryGrantsMap.get(category),
                    operatorId);
        }
        return position;
    }

    private String assignSecretKey(String abbr) {
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(abbr), "公司简称", abbr);
        try {
            String shortPinyin = PinyinHelper.getShortPinyin(abbr);
            for (int i = 0; i < 100; i++) {
                String secretKey = i > 0 ? String.format("%s%02d", shortPinyin, i) : shortPinyin;
                Company existed = repository.findBySecretKey(secretKey);
                if (existed == null) {
                    return secretKey;
                }
            }
            throw new EstateException(ExCode.COMPANY_ABBR_OVER_COUNT);
        } catch (PinyinException e) {
            throw ExceptionUtil.wrap(e);
        }
    }


    public Company findOne(Long id) {
        ExceptionUtil.checkNotNull("公司编号", id);
        return repository.findOne(id);
    }

    public List<Company> findYkRaCompany() {
        return repository.findYkRaCompany();
    }

    public PageList<CompanyDTO> list(Long cityId, Long parentId, CompanyDefine.Type companyType,
                                     PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("分页信息", pageBounds);
        PageList<CompanyDTO> list = repository.list(cityId, parentId, companyType, pageBounds);
        list.forEach(c -> {
            c.setPartA(Optional.ofNullable(companySigningService.getLastSigning(c.getId()))
                    .map(CompanySigningDTO::getPartA)
                    .orElse(null));
            c.setBoss(employeeService.selectDTOById(c.getBossId()));
            c.setDeptsCount(departmentService.countForCompany(c.getId()));
            c.setEmployeeCount(employeeService.countForCompany(c.getId()));
            if (c.getType() == CompanyDefine.Type.REGIONAL_AGENT) {
                c.setChannelCount(repository.countForParent(c.getId(), CompanyDefine.Type.CHANNEL));
                c.setSigleStoreCount(repository.countForParent(c.getId(), CompanyDefine.Type.SINGLE_STORE));
            }
        });
        return list;
    }

    @Transactional
    public Company updateInfo(Company company) {
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(company.getName()),
                "公司名", company.getName());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(company.getAbbr()),
                "公司简称", company.getAbbr());
        ExceptionUtil.checkNotNull("负责人编号", company.getBossId());
        repository.updateInfo(company);
        return repository.findOne(company.getId());
    }

    @Transactional
    public Company updateBoss(long companyId, long bossId) {
        Company oldCompany = repository.findOne(companyId);
        if (!Objects.equals(oldCompany.getBossId(), bossId)) {
            Employee newBoss = employeeService.findById(bossId);
            if (!Objects.equals(newBoss.getCompanyId(), companyId)) {
                throw new EstateException(ExCode.COMPANY_BOSS_NOT_IN);
            }
            employeeService.setIsBoss(oldCompany.getBossId(), false);
            employeeService.setIsBoss(bossId, true);
            repository.updateBossId(companyId, bossId);
        }
        return repository.findOne(companyId);
    }
}
