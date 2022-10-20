package com.innova.passwordvalidator.rule.impl;

import com.innova.passwordvalidator.rule.AbstractRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularExpressionRule extends AbstractRule {
    private String regexPattern;
    private boolean isPositive;

    public RegularExpressionRule(String regexPattern) {
        this.regexPattern = regexPattern;
        this.isPositive = true;
    }

    @Override
    public boolean check(String target) {
        boolean result = !isPositive;
        if (target != null) {
            result = isPositive == Pattern.matches(regexPattern, target);
        }
        return result;
    }

    @Override
    public String getName() {
        return String.format("%s == %s ?", this.regexPattern, this.isPositive);
    }
}
