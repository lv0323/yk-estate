package com.lyun.estate.biz.company;

import com.lyun.estate.biz.company.repo.CompanyRepository;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

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
        List<ObjectError> objectErrorList = new ArrayList<>();
        for (ConstraintViolation<T> cv : constraintViolationSet)
            objectErrorList.add(new ObjectError(cv.getRootBeanClass().getName(), cv.getMessage()));
        if (!constraintViolationSet.isEmpty())
            throw new ValidateException("ConstraintViolation", objectErrorList);
        return bean;
    }

    public Company create(Company company) {
        repository.insert(validate(company).setSecretKey(String.valueOf(new Date().getTime())));
        return company;
    }
}
