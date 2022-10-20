package com.innova.passwordvalidator.service.impl;

import com.innova.passwordvalidator.exception.ValidateServiceException;
import com.innova.passwordvalidator.model.ValidateServiceResultEntity;
import com.innova.passwordvalidator.service.ValidateService;

public class SampleValidateService implements ValidateService {
    @Override
    public ValidateServiceResultEntity validate(Object target) throws ValidateServiceException {
        // implement business logic here
        return null;
    }
}
