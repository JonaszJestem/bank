package com.jonaszwiacek.bank.controllers;

import com.jonaszwiacek.bank.models.LoginRequest;
import com.jonaszwiacek.bank.models.SignUpRequest;
import com.jonaszwiacek.bank.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest signUpRequest) {
        userService.signup(signUpRequest);
        return "OK.";
    }


    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String login(@RequestBody LoginRequest loginRequest) {
        return "{\"token\": \"" + userService.login(loginRequest) + "\"}";
    }


//    @PostMapping("/callPasswordReset")
//    public void callReset(@RequestBody String email) {
//        userService.callPasswordReset(email);
//    }
//
//
//    @PostMapping("/resetPassword/{resetToken}")
//    public void login(@PathVariable String resetToken, @RequestBody String newPassword) {
//        userService.resetPassword(resetToken, newPassword);
//    }

}
