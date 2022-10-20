package com.innova.passwordvalidator.common;

import com.innova.passwordvalidator.enums.ResponseCode;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private String code;
    private String message;
    private T resultEntity;

    public void catchException(BaseException be) {
        this.code = be.getCode();
        this.message = be.getCustomMessage();
    }

    public void response(ResponseCode responseCode, T entity) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        if (entity != null) this.resultEntity = entity;
    }
}
