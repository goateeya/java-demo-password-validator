package com.innova.passwordvalidator.service.impl;

import com.innova.passwordvalidator.enums.ResponseCode;
import com.innova.passwordvalidator.exception.ValidateServiceException;
import com.innova.passwordvalidator.model.ResultDetail;
import com.innova.passwordvalidator.model.ValidateServiceResultEntity;
import com.innova.passwordvalidator.rule.AbstractRule;
import com.innova.passwordvalidator.service.ValidateService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "passwordValidateService")
@Data
public class PasswordValidateService implements ValidateService {

    @Autowired
    @Qualifier(value = "passwordValidateServiceRuleList")
    private List<AbstractRule> rules;

    public ValidateServiceResultEntity validatePassword(String password) throws ValidateServiceException {
        ValidateServiceResultEntity result = null;
        if (rules != null && rules.size() > 0) {
            List<ResultDetail> details = rules.stream()
                    .map(x -> new ResultDetail(x.getName(), x.check(password)))
                    .collect(Collectors.toList());
            boolean isCheckPass = details.stream().allMatch(ResultDetail::isCheckPass);
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
