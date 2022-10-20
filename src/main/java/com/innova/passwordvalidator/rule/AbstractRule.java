package com.innova.passwordvalidator.rule;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class AbstractRule implements Rule {
    private String name;

    @Override
    public boolean check(String target) {
        log.warn("please implement check method");
        return false;
    }
}
