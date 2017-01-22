package com.lyun.estate.biz.employee.service;

import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.repo.EmployeeRepo;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.file.service.FileService;
import com.lyun.estate.core.config.CacheConfig;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeService {

    private static final String LOGIN_SALT_PREFIX = "LOGIN_SALT";
    private final EmployeeRepo repo;
    private final FileService fileService;
    private final Cache cache;

    public EmployeeService(EmployeeRepo repo, @Qualifier("evictCacheManager") CacheManager cacheManager, FileService fileService) {
        this.repo = repo;
        cache = cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME);
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

    private <T> T validate(T bean) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(Objects.requireNonNull(bean));
        if (!constraintViolationSet.isEmpty())
            throw new ValidateException("ConstraintViolation", "参数不合法");
        return bean;
    }

    public Employee create(Employee employee) {
        repo.insert(validate(employee));
        return employee;
    }

    public List<Employee> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(Objects.requireNonNull(companyId));
    }

    public Employee update(Employee employee) {
        Objects.requireNonNull(Objects.requireNonNull(employee).getId());
        repo.update(validate(employee));
        return repo.selectById(employee.getId());
    }

    public String getAvatar(Long id) {
        FileDescription fd = fileService.findFirst(id, DomainType.AVATAR, null, null);
        return fd != null ? fd.getFileURI() : null;
    }

    public Employee createAvatar(Long id, InputStream avatarIS, String suffix) {
        repo.avatar(id, fileService.save(new FileDescription()
                .setOwnerId(id)
                .setOwnerType(DomainType.AVATAR)
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
}
