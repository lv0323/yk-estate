package com.lyun.estate.biz.employee.service;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.biz.company.repo.CompanyRepository;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.repo.EmployeeRepo;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.file.service.FileService;
import com.lyun.estate.biz.utils.clock.ClockTools;
import com.lyun.estate.core.config.CacheConfig;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.utils.Validations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmployeeService {

    private static final String LOGIN_SALT_PREFIX = "LOGIN_SALT";
    private final EmployeeRepo repo;
    private final CompanyRepository companyRepository;
    private final FileService fileService;
    private final Cache cache;
    private final ClockTools clockTools;

    public EmployeeService(EmployeeRepo repo, @Qualifier("evictCacheManager") CacheManager cacheManager, CompanyRepository companyRepository, FileService fileService, ClockTools clockTools) {
        this.repo = repo;
        cache = cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME);
        this.companyRepository = companyRepository;
        this.fileService = fileService;
        this.clockTools = clockTools;
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
        repo.insert(Validations.doValidate(employee));
        return employee;
    }

    public Employee createBoss(Employee employee) {
        repo.insert(Validations.doValidate(employee).setIsBoss(Boolean.TRUE));
        return employee;
    }

    public List<Employee> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }

    public Employee update(Employee employee) {
        Objects.requireNonNull(Objects.requireNonNull(employee).getId());
        repo.update(Validations.doValidate(employee));
        return repo.selectById(employee.getId());
    }

    public String getAvatar(Long id) {
        FileDescription fd = fileService.findFirst(id, DomainType.EMPLOYEE, CustomType.AVATAR, null);
        return fd != null ? fd.getFileURI() : null;
    }

    public Employee createAvatar(Long id, InputStream avatarIS, String suffix) {
        repo.avatar(id, fileService.save(new FileDescription()
                .setOwnerId(id)
                .setOwnerType(DomainType.EMPLOYEE)
                .setCustomType(CustomType.AVATAR)
                .setFileType(FileType.IMAGE), avatarIS, suffix).getId());
        return repo.selectById(id);
    }

    public Boolean quit(Long id) {
        repo.quit(id);
        return true;
    }

    public Employee selectByMobile(String mobile) {
        return repo.selectByMobile(Objects.requireNonNull(mobile));
    }

    public Boolean active(String mobile, String password, String secretKey) {
        Objects.requireNonNull(mobile);
        Objects.requireNonNull(password);
        Objects.requireNonNull(secretKey);
        String salt = UUID.randomUUID().toString().replace("-", "");
        return repo.active(mobile, hmac(salt, password), salt, secretKey) == 1;
    }

    public String sugar(String mobile) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        cache.put(LOGIN_SALT_PREFIX + Objects.requireNonNull(mobile), salt);
        return salt;
    }

    public Employee login(String mobile, String sugaredPassword) {
        Objects.requireNonNull(mobile);
        Objects.requireNonNull(sugaredPassword);
        Employee employee = repo.selectByMobile(mobile);
        if (employee == null)
            throw new EstateException(ExCode.LOGIN_FAIL);
        checkCompany(employee.getCompanyId());
        String rawPassword = employee.getPassword();
        if (rawPassword == null)
            throw new EstateException(ExCode.NOT_ACTIVE_EMPLOYEE);
        String sugar = cache.get(LOGIN_SALT_PREFIX + mobile, String.class);
        if (sugar == null)
            throw new EstateException(ExCode.NO_SUGAR);
        if (!hmac(sugar, rawPassword).equals(sugaredPassword))
            throw new EstateException(ExCode.WRONG_PASSWORD);
        cache.evict(LOGIN_SALT_PREFIX + mobile);
        return employee;
    }

    private void checkCompany(Long companyId) {
        Company company = companyRepository.selectOne(companyId);
        if (company.getLocked())
            throw new EstateException(ExCode.COMPANY_LOCKED);
        if (company.getEndDate().getTime() < clockTools.now().getTime())
            throw new EstateException(ExCode.COMPANY_EXPIRED);
    }
}
