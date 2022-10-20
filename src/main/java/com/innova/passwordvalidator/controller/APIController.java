package com.innova.passwordvalidator.controller;

import com.innova.passwordvalidator.common.BaseRequest;
import com.innova.passwordvalidator.common.BaseResponse;
import com.innova.passwordvalidator.enums.ResponseCode;
import com.innova.passwordvalidator.exception.ValidateServiceException;
import com.innova.passwordvalidator.model.ValidatePasswordRequestEntity;
import com.innova.passwordvalidator.model.ValidateServiceResultEntity;
import com.innova.passwordvalidator.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class APIController {
    @Autowired
    private ValidateService passwordValidateService;

    @PostMapping(value = "validatePassword")
    public BaseResponse<ValidateServiceResultEntity> validatePassword(@Valid @RequestBody BaseRequest<ValidatePasswordRequestEntity> request) {
        BaseResponse<ValidateServiceResultEntity> result = new BaseResponse<>();

        String password = request.getRequestEntity().getPassword();
        try {
            ValidateServiceResultEntity entity = passwordValidateService.validate(password);
            if (entity != null && entity.isCheckPass()) {
                result.response(ResponseCode.SUCCESS, entity);
            } else {
                result.response(ResponseCode.FAIL, entity);
            }
        } catch (ValidateServiceException e) {
            result.catchException(e);
        }

        return result;
    }
}
