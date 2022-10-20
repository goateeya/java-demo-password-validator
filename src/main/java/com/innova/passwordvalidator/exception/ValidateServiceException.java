package com.innova.passwordvalidator.exception;

import com.innova.passwordvalidator.common.BaseException;
import com.innova.passwordvalidator.enums.ResponseCode;

public class ValidateServiceException extends BaseException {
    public ValidateServiceException(ResponseCode code) {
        this.setCode(code.getCode());
        this.setCustomMessage(code.getMessage());
    }

    public ValidateServiceException(ResponseCode code, String customMessage) {
        this.setCode(code.getCode());
        this.setCustomMessage(customMessage);
    }
}
