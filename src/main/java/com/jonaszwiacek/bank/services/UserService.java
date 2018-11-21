package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.LoginDto;
import com.jonaszwiacek.bank.models.User;
import com.jonaszwiacek.bank.models.UserDto;
import com.jonaszwiacek.bank.repositories.UserRepository;
import com.jonaszwiacek.bank.services.exceptions.UserAlreadyExistException;
import com.jonaszwiacek.bank.services.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserService {
    private UserRepository userRepository;
    private EmailSender emailSender;


    public UserService(
            UserRepository userRepository, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;

        userRepository.save(new User("login", "email", "password"));
    }


    public void signup(UserDto userDto) {
        System.out.println(userDto);
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new UserAlreadyExistException();
        }
        User user = new User(
                userDto.getLogin(),
                userDto.getEmail(),
                userDto.getPassword()
        );
        userRepository.save(user);
    }


    public String login(LoginDto loginDetails) {
        User user = userRepository.findByLogin(loginDetails.getLogin());
        if (user != null && user.getPassword().equals(loginDetails.getPassword())) {
            return "{token: TOKEN}";
        }
        throw new UserNotFoundException();
    }


    public void callPasswordReset(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            String token = ResetTokenGenerator.nextPassword();
            user.setResetToken(token);
            user.setResetTimestamp(new Date());

            emailSender.sendResetToken(email, token);
            userRepository.save(user);
        }
    }


    public void resetPassword(String resetToken, String newPassword) {
        if (resetToken.isEmpty()) {
            return;
        }

        User user = userRepository.findByResetToken(resetToken);

        if (user != null) {
            user.setResetToken("");
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }
}
