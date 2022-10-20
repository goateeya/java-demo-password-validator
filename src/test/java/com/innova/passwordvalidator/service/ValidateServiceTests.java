package com.innova.passwordvalidator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidateServiceTests {
    @Autowired
    private ValidateService passwordValidateService;

    @Test
    public void testPasswordValidateService() {
        assertAll(
            () -> assertTrue(passwordValidateService.validate("123456a").isCheckPass()),
                () -> assertTrue(passwordValidateService.validate("123456abc").isCheckPass()),
                () -> assertTrue(passwordValidateService.validate("123456789abc").isCheckPass()),
                () -> assertTrue(passwordValidateService.validate("abc1abc").isCheckPass()),
                () -> assertFalse(passwordValidateService.validate("!@#qwe123").isCheckPass()),
                () -> assertFalse(passwordValidateService.validate("123456aA").isCheckPass()),
                () -> assertFalse(passwordValidateService.validate("123456aa").isCheckPass()),
                () -> assertFalse(passwordValidateService.validate("123123").isCheckPass()),
                () -> assertFalse(passwordValidateService.validate("1234567890abc").isCheckPass())
        );
    }
}
