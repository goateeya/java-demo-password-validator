package com.innova.passwordvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateServiceResultEntity {
    private boolean checkPass;
    private List<ResultDetail> details;
}
