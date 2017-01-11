package com.lyun.estate.biz.employee.service;

import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.repo.EmployeeRepo;
import com.lyun.estate.core.config.CacheConfig;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmployeeService {

    private static final String LOGIN_SALT_PREFIX = "LOGIN_SALT";
    private final EmployeeRepo repo;
    private final Cache cache;

    public EmployeeService(EmployeeRepo repo, @Qualifier("evictCacheManager") CacheManager cacheManager) {
        this.repo = repo;
        cache = cacheManager.getCache(CacheConfig.EVICT_CACHE_NAME);
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
        repo.insert(Objects.requireNonNull(employee));
        return employee;
    }

    public List<Employee> selectByCompanyId(Long companyId) {
        return repo.selectByCompanyId(companyId);
    }

    public Employee update(Employee employee) {
        repo.update(Objects.requireNonNull(employee));
        return repo.selectById(employee.getId());
    }

    public Boolean deleteById(Long id) {
        return repo.deleteById(id) == 1;
    }

    public Employee selectByMobile(String mobile) {
        return repo.selectByMobile(mobile);
    }

    public Boolean active(String mobile, String password, String secretKey) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        return repo.active(mobile, hmac(salt, password), salt, secretKey) == 1;
    }

    public String salt(String mobile) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        cache.put(LOGIN_SALT_PREFIX + mobile, salt);
        return salt;
    }

    public Employee login(String mobile, String password) {
        Employee employee = repo.selectByMobile(mobile);
        String rawPassword = employee.getPassword();
        if (rawPassword == null)
            return null;
        String salt = cache.get(LOGIN_SALT_PREFIX + mobile, String.class);
        if (salt == null)
            return null;
        if (!hmac(salt, rawPassword).equals(password))
            return null;
        cache.evict(LOGIN_SALT_PREFIX + mobile);
        return employee;
    }
}
