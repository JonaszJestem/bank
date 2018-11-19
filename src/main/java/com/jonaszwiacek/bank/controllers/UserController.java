package com.jonaszwiacek.bank.controllers;

import com.jonaszwiacek.bank.models.User;
import com.jonaszwiacek.bank.models.LoginDto;
import com.jonaszwiacek.bank.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signup(User user) {
        userService.signup(user);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {
        userService.login(loginDto);
    }

    @PostMapping("/resetPassword")
    public void login(@RequestBody String email) {
        userService.resetPassword(email);
    }
}