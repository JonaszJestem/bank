package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.LoginDto;
import com.jonaszwiacek.bank.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void nonExistingUserCantLogin() {
        LoginDto loginDto = new LoginDto("login", "password");

        assertFalse(userService.login(loginDto));
    }

    @Test
    public void userCanSignup() {
        User user = new User("login", "email", "password");
        LoginDto loginDto = new LoginDto("login", "password");

        userService.signup(user);

        assertTrue(userService.login(loginDto));
    }

    @Test
    public void cantLoginWithInvalidPassword() {
        User user = new User("login", "email", "password");
        LoginDto invalidPassword = new LoginDto("login", "invalid");

        userService.signup(user);

        assertFalse(userService.login(invalidPassword));
    }
}