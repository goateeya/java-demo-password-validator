package com.innova.passwordvalidator.controller;

import com.innova.passwordvalidator.exception.ValidateServiceException;
import com.innova.passwordvalidator.model.ValidateServiceResultEntity;
import com.innova.passwordvalidator.service.impl.PasswordValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    @Autowired
    private PasswordValidateService passwordValidateService;

    @RequestMapping("/welcome")
    public String welcome(Model model, @RequestParam(value = "name", defaultValue = "guest") String name) {
        model.addAttribute("name", name);
        return "welcome";
    }

    @RequestMapping("/validatePassword")
    public String validatePassword() {
        return "validatePassword";
    }

    @PostMapping("/validatePassword")
    public String validatePassword(Model model, String password) {
        if (password != null) {
            try {
                ValidateServiceResultEntity entity = passwordValidateService.validatePassword(password);
                if (entity != null && entity.isCheckPass()) {
                    model.addAttribute("result", "success");
                } else {
                    model.addAttribute("result", "fail");
                }
            } catch (ValidateServiceException e) {
                model.addAttribute("result", "error");
            }
        }
        return "validatePassword";
    }
}
