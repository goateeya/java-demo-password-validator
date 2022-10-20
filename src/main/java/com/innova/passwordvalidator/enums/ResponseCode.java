package com.innova.passwordvalidator.enums;

import lombok.Getter;

public enum ResponseCode {
    SUCCESS("000", "success"),
    SUCCESS_WITH_WARNING("002", "success with warning"),
    FAIL("900", "fail"),
    ERROR("999", "error");

    @Getter private String code;
    @Getter private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
