package ru.job4j.forum.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.models.User;
import ru.job4j.forum.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class UserControl {

    private final UserService userService;

    public UserControl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        log.debug("Start /login");
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect!";
            log.debug("error");
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out!";
            log.debug("logout");
        }
        log.debug("error is: {}", error);
        log.debug("logout is: {}", logout);
        model.addAttribute("errorMessage", errorMessage);
        log.debug("error message: {}", errorMessage);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        log.debug("logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        log.debug("End logout");
        return "redirect:/login?logout=true";
    }

    @GetMapping("/reg")
    public String reg() {
        return "/reg";
    }

    @PostMapping("/reg")
    public String registration(@ModelAttribute User user) {
        log.debug("User: {}", user);
        return userService.registration(user) ? "redirect:/login" : reg();
    }
}