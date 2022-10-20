package com.innova.passwordvalidator.common;

import lombok.Data;

@Data
abstract public class BaseException extends Exception {
    private String code;
    private String customMessage;
}
