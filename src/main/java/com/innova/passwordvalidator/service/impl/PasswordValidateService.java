package com.innova.passwordvalidator.service.impl;

import com.innova.passwordvalidator.config.PasswordValidateConfig;
import com.innova.passwordvalidator.enums.ResponseCode;
import com.innova.passwordvalidator.exception.ValidateServiceException;
import com.innova.passwordvalidator.model.ResultDetail;
import com.innova.passwordvalidator.model.ValidateServiceResultEntity;
import com.innova.passwordvalidator.rule.AbstractRule;
import com.innova.passwordvalidator.rule.impl.RegularExpressionRule;
import com.innova.passwordvalidator.service.ValidateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "passwordValidateService")
@Data
public class PasswordValidateService implements ValidateService {

    private List<AbstractRule> ruleList;

    @Autowired
    public PasswordValidateService(PasswordValidateConfig passwordValidateConfig) {
        this.ruleList = new ArrayList<>();
        this.ruleList.addAll(passwordValidateConfig.getPositiveRegexPatternList().stream()
                .map(x -> new RegularExpressionRule(x, true))
                .collect(Collectors.toList()));
        this.ruleList.addAll(passwordValidateConfig.getNegativeRegexPatternList().stream()
                .map(x -> new RegularExpressionRule(x, false))
                .collect(Collectors.toList()));
    }

    public ValidateServiceResultEntity validatePassword(String password) throws ValidateServiceException {
        ValidateServiceResultEntity result = null;
        if (ruleList != null && ruleList.size() > 0) {
            List<ResultDetail> details = ruleList.stream()
                    .map(x -> new ResultDetail(x.getName(), x.check(password)))
                    .collect(Collectors.toList());
            boolean isCheckPass = details.stream().allMatch(x -> x.isCheckPass());
            result = new ValidateServiceResultEntity(isCheckPass, details);
        } else {
            throw new ValidateServiceException(ResponseCode.ERROR, "no rule config");
        }
        return result;
    }

    @Override
    public ValidateServiceResultEntity validate(Object target) throws ValidateServiceException {
        ValidateServiceResultEntity result = null;
        if (target instanceof String) {
            result = validatePassword((String) target);
        }
        return result;
    }
}