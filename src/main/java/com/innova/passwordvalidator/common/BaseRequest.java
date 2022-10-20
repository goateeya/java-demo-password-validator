package com.innova.passwordvalidator.common;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class BaseRequest<T> {
    private String bpId;
    @NotNull @Valid
    private T requestEntity;
}
