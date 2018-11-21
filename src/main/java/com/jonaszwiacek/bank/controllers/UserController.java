package com.jonaszwiacek.bank.controllers;

import com.jonaszwiacek.bank.models.LoginDto;
import com.jonaszwiacek.bank.models.User;
import com.jonaszwiacek.bank.models.UserDto;
import com.jonaszwiacek.bank.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;


@RestController
@RequestMapping( "/user" )
public class UserController
{
    private UserService userService;


    public UserController( UserService userService )
    {
        this.userService = userService;
    }


    @PostMapping( "/signup" )
    public void signup(@RequestBody @Valid UserDto user )
    {
        userService.signup( user );
    }


    @PostMapping( "/login" )
    public String login(@RequestBody LoginDto loginDto )
    {
        return userService.login( loginDto );
    }


    @PostMapping( "/callPasswordReset" )
    public void callReset( @RequestBody String email )
    {
        userService.callPasswordReset( email );
    }


    @PostMapping( "/resetPassword/{resetToken}" )
    public void login( @PathVariable String resetToken, @RequestBody String newPassword )
    {
        userService.resetPassword( resetToken, newPassword );
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void validationError(ValidationException ex) {
    }
}
