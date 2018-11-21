package com.jonaszwiacek.bank.controllers;

import com.jonaszwiacek.bank.models.LoginDto;
import com.jonaszwiacek.bank.models.UserDto;
import com.jonaszwiacek.bank.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public String signup(@RequestBody @Valid UserDto user) {
        userService.signup(user);
        return "OK.";
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }


    @PostMapping("/callPasswordReset")
    public void callReset(@RequestBody String email) {
        userService.callPasswordReset(email);
    }


    @PostMapping("/resetPassword/{resetToken}")
    public void login(@PathVariable String resetToken, @RequestBody String newPassword) {
        userService.resetPassword(resetToken, newPassword);
    }

}
