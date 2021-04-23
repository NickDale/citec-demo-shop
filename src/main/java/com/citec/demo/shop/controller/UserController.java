package com.citec.demo.shop.controller;

import com.citec.demo.shop.model.entity.User;
import com.citec.demo.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import static java.util.Objects.isNull;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPanel(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        if (isNull(user)) {
            return "error/404";
        }
        model.addAttribute("user", user);
        return "user";
    }
}
