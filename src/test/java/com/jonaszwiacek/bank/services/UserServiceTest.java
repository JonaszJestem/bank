package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.LoginDto;
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
    @Autowired
    private UserService userService;

    @Test(expected = UserNotFoundException.class)
    public void nonExistingUserDtoCantLogin() {
        LoginDto loginDto = new LoginDto("login", "password");

        userService.login(loginDto);
    }

    @Test
    public void userCanSignup() {
        UserDto user = new UserDto("login", "email", "password");
        LoginDto loginDto = new LoginDto("login", "password");

        userService.signup(user);

        userService.login(loginDto);
    }

    @Test(expected = UserNotFoundException.class)
    public void cantLoginWithInvalidPassword() {
        UserDto user = new UserDto("login", "email", "password");
        LoginDto invalidPassword = new LoginDto("login", "invalid");

        userService.signup(user);

        userService.login(invalidPassword);
    }
}
