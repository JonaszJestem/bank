package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.LoginDto;
import com.jonaszwiacek.bank.models.User;
import com.jonaszwiacek.bank.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(@Valid User user) {
        userRepository.save(user);
    }

    public boolean login(LoginDto loginDetails) {
        User user = userRepository.findByLogin(loginDetails.getLogin());
        if (user != null) {
            return user.getPassword().equals(loginDetails.getPassword());
        }
        return false;
    }

    public void resetPassword(String email) {

    }
}
