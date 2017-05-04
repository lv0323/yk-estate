package com.lyun.estate.biz.employee.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.repo.EmployeeRepo;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.config.EstateCacheConfig;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeService {

    private static final String LOGIN_SUGAR_PREFIX = "LOGIN_SUGAR";
    private static final String CHANGE_PSWD_SUGAR_PREFIX = "LOGIN_SUGAR";
    private final EmployeeRepo repo;
    private final Cache cache;
    private final FileService fileService;
    @Autowired
    private DepartmentService departmentService;

    public EmployeeService(EmployeeRepo repo,
                           @Qualifier(EstateCacheConfig.MANAGER_10_5K) CacheManager cacheManager,
                           FileService fileService) {
        this.repo = repo;
        this.cache = cacheManager.getCache(EstateCacheConfig.MGT_LOGIN_CACHE);
        this.fileService = fileService;
    }

    private static String hmac(String salt, String password) {
        try {
            SecretKey secretKey = new SecretKeySpec(salt.getBytes(), "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKey);
            return new BigInteger(1, mac.doFinal(password.getBytes())).toString(16);
        } catch (Exception e) {
            throw new EstateException(ExCode.DEFAULT_EXCEPTION);
        }
    }

    public Employee create(Employee employee) {
        ExceptionUtil.checkNotNull("用户数据", employee);
        ExceptionUtil.checkNotNull("公司编号", employee.getCompanyId());
        ExceptionUtil.checkNotNull("部门编号", employee.getDepartmentId());
        ExceptionUtil.checkNotNull("岗位编号", employee.getPositionId());
        ExceptionUtil.checkNotNull("加入时间", employee.getEntryDate());
        ExceptionUtil.checkNotNull("状态", employee.getStatus());
        ExceptionUtil.checkIllegal(ValidateUtil.isMobile(employee.getMobile()), "用户手机", employee.getMobile());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(employee.getName()), "用户名", employee.getName());
        repo.insert(employee);
        return repo.selectById(employee.getId());
    }

    public PageList<Employee> listByCompanyIdDepartmentId(Long companyId,
                                                          Long departmentId,
                                                          PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("公司编号", companyId);
        Set<Long> childs = null;
        if (departmentId != null) {
            childs = departmentService.findChildIds(companyId, departmentId);
            if (CollectionUtils.isEmpty(childs)) {
                return null;
            }
        }
        return repo.selectByCompanyIdAndDeptIds(companyId, childs, pageBounds);
    }

    public Employee update(Employee employee) {
        ExceptionUtil.checkNotNull("用户数据", employee);
        ExceptionUtil.checkNotNull("用户编号", employee.getId());
        repo.update(employee);
        return repo.selectById(employee.getId());
    }

    public Boolean quit(Long id) {
        Employee employee = repo.selectById(id);
        if (employee == null) {
            return false;
        } else if (employee.getBoss()) {
            throw new EstateException(ExCode.EMPLOYEE_IS_BOSS);
        }
        repo.quit(id);
        return true;
    }

    public Employee selectByMobile(String mobile) {
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(mobile), "用户手机", mobile);
        return repo.selectByMobile(mobile);
    }

    public Boolean active(String mobile, String password, String secretKey) {
        Objects.requireNonNull(mobile);
        ExceptionUtil.checkIllegal(ValidateUtil.isPassword(password), "密码", "");
        Objects.requireNonNull(secretKey);
        Employee employee = repo.selectByMobile(mobile);
        if (employee == null) {
            throw new EstateException(ExCode.NOT_FOUND, "手机号", "员工");
        } else if (!Strings.isNullOrEmpty(employee.getPassword())) {
            throw new EstateException(ExCode.EMPLOYEE_ACTIVE);
        }
        String salt = UUID.randomUUID().toString().replace("-", "");
        return repo.active(mobile, hmac(salt, password), salt, secretKey) == 1;
    }

    public String sugar(String mobile) {
        String sugar = UUID.randomUUID().toString().replace("-", "");
        cache.put(LOGIN_SUGAR_PREFIX + Objects.requireNonNull(mobile), sugar);
        return sugar;
    }

    public Employee login(String mobile, String sugaredPassword) {
        Objects.requireNonNull(mobile);
        Objects.requireNonNull(sugaredPassword);
        Employee employee = repo.selectByMobile(mobile);
        if (employee == null)
            throw new EstateException(ExCode.EMPLOYEE_LOGIN_FAIL);
        checkCompany(employee.getCompanyId());
        String rawPassword = employee.getPassword();
        if (rawPassword == null)
            throw new EstateException(ExCode.EMPLOYEE_NOT_ACTIVE);
        String sugar = cache.get(LOGIN_SUGAR_PREFIX + mobile, String.class);
        if (sugar == null)
            throw new EstateException(ExCode.EMPLOYEE_NO_SUGAR);
        if (!hmac(sugar, rawPassword).equals(sugaredPassword))
            throw new EstateException(ExCode.EMPLOYEE_WRONG_PASSWORD);
        cache.evict(LOGIN_SUGAR_PREFIX + mobile);
        return employee;
    }

    public Employee selectById(Long id) {
        return repo.selectById(id);
    }

    private boolean checkCompany(Long companyId) {
        return true;
    }

    public String getAvatarURI(Long id) {
        Employee employee = repo.selectById(id);
        if (employee != null && employee.getAvatarId() != null) {
            return fileService.findOne(employee.getAvatarId()).getFileURI();
        } else {
            return null;
        }
    }

    public FileDescription createAvatar(Long id, InputStream avatarIS, String suffix) {

        FileDescription fileDescription = fileService.save(new FileDescription()
                        .setOwnerId(id)
                        .setOwnerType(DomainType.EMPLOYEE)
                        .setCustomType(CustomType.AVATAR)
                        .setFileType(FileType.IMAGE)
                        .setFileProcess(FileProcess.NONE.getFlag())
                , avatarIS, suffix);

        repo.avatar(id, fileDescription.getId());
        return fileService.findOne(fileDescription.getId());
    }

    public String sugarById(Long id) {
        String sugar = UUID.randomUUID().toString().replace("-", "");
        cache.put(CHANGE_PSWD_SUGAR_PREFIX + Objects.requireNonNull(id), sugar);
        return sugar;
    }

    public Boolean changePassword(Long id, String sugaredPassword, String saltedNewPassword) {
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(saltedNewPassword), "密码", "");
        Employee employee = repo.selectById(id);
        if (employee == null) {
            throw new EstateException(ExCode.EMPLOYEE_NOT_FOUND);
        }
        if (Strings.isNullOrEmpty(employee.getPassword())) {
            throw new EstateException(ExCode.EMPLOYEE_NOT_ACTIVE);
        }
        String sugar = cache.get(CHANGE_PSWD_SUGAR_PREFIX + id, String.class);
        if (sugar == null)
            throw new EstateException(ExCode.EMPLOYEE_NO_SUGAR);
        if (!hmac(sugar, employee.getPassword()).equals(sugaredPassword)) {
            throw new EstateException(ExCode.EMPLOYEE_WRONG_PASSWORD);
        } else {
            cache.evict(CHANGE_PSWD_SUGAR_PREFIX + id);
            return repo.updatePassword(new Employee().setId(id).setPassword(saltedNewPassword)) > 1;
        }
    }

    public Boolean resetPassword(Long id, String newPassword) {
        ExceptionUtil.checkNotNull("员工编号", id);
        ExceptionUtil.checkIllegal(ValidateUtil.isPassword(newPassword), "密码", "");
        Employee employee = repo.selectById(id);
        if (employee == null) {
            throw new EstateException(ExCode.EMPLOYEE_NOT_FOUND);
        } else if (Strings.isNullOrEmpty(employee.getPassword())) {
            throw new EstateException(ExCode.EMPLOYEE_NOT_ACTIVE);
        }
        return repo.updatePassword(new Employee().setId(id).setPassword(hmac(employee.getSalt(), newPassword))) > 0;
    }

    public Employee updateContact(Long id, String openContact, String weChat) {
        Employee employee = new Employee().setId(id).setOpenContact(openContact)
                .setWechat(weChat);
        if (repo.updateContact(employee) > 0) {
            return repo.selectById(id);
        }
        throw new EstateException(ExCode.UPDATE_FAIL, employee.toString(), "员工信息");
    }

    public Boolean updateDeviceId(Long id, String deviceId) {
        return repo.updateDeviceId(id, deviceId) > 0;
    }

    public Employee findById(long id) {
        return repo.selectById(id);
    }

    @Transactional
    public boolean updateFollowFangId(long id, long fangId, BizType bizType) {
        ExceptionUtil.checkNotNull("业务类别", bizType);
        Employee employee = repo.selectById(id);
        int contactCount = 1;
        if (bizType == BizType.SELL && !Objects.equals(fangId, employee.getFollowFangId())) {
            if (employee.getLastSellCountTime() != null
                    && employee.getLastSellCountTime().toInstant()
                    .isAfter(CommonUtil.startOfToday())) {
                contactCount = employee.getSellContactCount() + 1;
            }
            return repo.updateFollowSell(id, fangId, contactCount) > 0;
        } else if (bizType == BizType.RENT && !Objects.equals(fangId, employee.getFollowRentId())) {
            if (employee.getLastRentCountTime() != null
                    && employee.getLastRentCountTime().toInstant().isAfter(CommonUtil.startOfToday())) {
                contactCount = employee.getRentContactCount() + 1;
            }
            return repo.updateFollowRent(id, fangId, contactCount) > 0;
        }
        return false;
    }

    public boolean clearFollowFangId(Long id, Long fangId, BizType bizType) {
        ExceptionUtil.checkNotNull("业务类别", bizType);
        if (bizType == BizType.SELL) {
            return repo.clearFollowSell(id, fangId) > 0;
        } else {
            return repo.clearFollowRent(id, fangId) > 0;
        }
    }

    public int clearAllFollowFangId(Long fangId, BizType bizType) {
        ExceptionUtil.checkNotNull("业务类别", bizType);
        if (bizType == BizType.SELL) {
            return repo.clearAllFollowSell(fangId);
        } else {
            return repo.clearAllFollowRent(fangId);
        }
    }

    public List<Employee> listByCompanyIdAndPositionId(long companyId, long positionId) {
        return repo.listByCompanyIdAndPositionId(companyId, positionId);
    }
}
