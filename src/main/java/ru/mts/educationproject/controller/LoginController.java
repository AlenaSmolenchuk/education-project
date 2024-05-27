package ru.mts.educationproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mts.educationproject.annotations.Logging;

@Controller
public class LoginController {

    @Logging(value = "Login page",
            enter = true,
            exit = true)
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @Logging(value = "Logout page",
            enter = true,
            exit = true)
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @Logging(value = "Access denied page",
            enter = true,
            exit = true)
    @GetMapping("/no-access")
    public String noAccess() {
        return "no-access";
    }
}
