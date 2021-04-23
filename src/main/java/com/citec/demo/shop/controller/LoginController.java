package com.citec.demo.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static java.util.Objects.nonNull;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String error) {
        if (nonNull(error))
            model.addAttribute("error", "Your username and password is invalid.");

        return "login";
    }
}
