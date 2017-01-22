package com.lyun.estate.core.utils;

import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Validations {

    private static final javax.validation.Validator VALIDATOR;

    static {
        ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    public static <T> T doValidate(T bean) {
        Set<ConstraintViolation<T>> constraintViolationSet = VALIDATOR.validate(Objects.requireNonNull(bean));
        if (!constraintViolationSet.isEmpty()) {
            List<ObjectError> objectErrorList = new ArrayList<>();
            for (ConstraintViolation<T> cv : constraintViolationSet)
                objectErrorList.add(new ObjectError(cv.getRootBeanClass().getName(), cv.getMessage()));
            throw new ValidateException("ConstraintViolation", objectErrorList);
        }
        return bean;
    }
}
