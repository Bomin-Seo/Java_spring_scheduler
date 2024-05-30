package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.LoginRequestDto;
import com.sparta.scheduler.dto.SignupRequestDto;
import com.sparta.scheduler.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/mainpage")
    public String mainPage() {
        return "mainpage";
    }

    @GetMapping("/user/schedulepage")
    public String schedulePage() {
        return "schedules";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse response){
        try {
            userService.login(requestDto, response);
        } catch (Exception e) {
            return "redirect:/api/user/login-page";
        }
        return "mainpage";
    }
}