package com.innova.passwordvalidator.config;

import com.innova.passwordvalidator.rule.AbstractRule;
import com.innova.passwordvalidator.rule.Rule;
import com.innova.passwordvalidator.rule.impl.RegularExpressionRule;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "validate-service.password-validate")
@Data
public class PasswordValidateConfig {
    private List<String> positiveRegexPatternList;
    private List<String> negativeRegexPatternList;

    @Bean(value = "passwordValidateServiceRuleList")
    public List<AbstractRule> getRuleList() {
        List<AbstractRule> result = new ArrayList<>();
        result.addAll(getRuleListFromPatternList(this.positiveRegexPatternList, true));
        result.addAll(getRuleListFromPatternList(this.negativeRegexPatternList, false));
        return result;
    }

    private List<AbstractRule> getRuleListFromPatternList(List<String> list, boolean isPositive) {
        return list.stream()
                .map(x -> new RegularExpressionRule(x, isPositive))
                .collect(Collectors.toList());
    }
}
