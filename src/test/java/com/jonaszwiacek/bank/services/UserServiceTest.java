package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.LoginRequest;
import com.jonaszwiacek.bank.models.UserDto;
import com.jonaszwiacek.bank.services.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
//    @Autowired
//    private UserService userService;
//
//    @Test(expected = UserNotFoundException.class)
//    public void nonExistingUserDtoCantLogin() {
//        LoginRequest loginRequest = new LoginRequest("test", "password");
//
//        userService.login(loginRequest);
//    }
//
//    @Test
//    public void userCanSignup() {
//        UserDto user = new UserDto("test", "email", "password");
//        LoginRequest loginRequest = new LoginRequest("test", "password");
//
//        userService.signup(user);
//
//        userService.login(loginRequest);
//    }
//
//    @Test(expected = UserNotFoundException.class)
//    public void cantLoginWithInvalidPassword() {
//        UserDto user = new UserDto("test", "email", "password");
//        LoginRequest invalidPassword = new LoginRequest("test", "invalid");
//
//        userService.signup(user);
//
//        userService.login(invalidPassword);
//    }
}
