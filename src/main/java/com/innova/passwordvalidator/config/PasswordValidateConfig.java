package com.innova.passwordvalidator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "validate-service.password-validate")
@Data
public class PasswordValidateConfig {
    private List<String> positiveRegexPatternList;
    private List<String> negativeRegexPatternList;
}
