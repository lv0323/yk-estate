package com.lyun.estate.biz.company;

import com.lyun.estate.biz.company.repo.CompanyRepository;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Set;

@Service
public class CompanyService {

    private static final Validator VALIDATOR;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    private static <T> T validate(T bean) {
        Set<ConstraintViolation<T>> constraintViolationSet = VALIDATOR.validate(Objects.requireNonNull(bean));
        if (!constraintViolationSet.isEmpty())
            throw new ValidateException("ConstraintViolation", "参数不合法");
        return bean;
    }

    public Company create(Company company) {
        repository.insert(validate(company));
        return company;
    }
}
