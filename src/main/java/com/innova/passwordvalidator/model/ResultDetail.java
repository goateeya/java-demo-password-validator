package com.innova.passwordvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDetail {
    private String stage;
    private boolean checkPass;
}
