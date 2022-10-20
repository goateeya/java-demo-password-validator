package com.innova.passwordvalidator.service;

import com.innova.passwordvalidator.exception.ValidateServiceException;
import com.innova.passwordvalidator.model.ValidateServiceResultEntity;

public interface ValidateService {
    ValidateServiceResultEntity validate(Object target) throws ValidateServiceException;
}
