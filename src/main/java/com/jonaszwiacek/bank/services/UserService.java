package com.jonaszwiacek.bank.services;

import com.jonaszwiacek.bank.models.*;
import com.jonaszwiacek.bank.repositories.RoleRepository;
import com.jonaszwiacek.bank.repositories.UserRepository;
import com.jonaszwiacek.bank.security.JwtTokenProvider;
import com.jonaszwiacek.bank.services.exceptions.UserAlreadyExistException;
import com.jonaszwiacek.bank.services.exceptions.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserService {
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider tokenProvider;


    public UserService(
            UserRepository userRepository, AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.tokenProvider = tokenProvider;

        roleRepository.save(new Role(RoleName.ROLE_USER));
        roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("email@email.com");
        signUpRequest.setUsername("login");
        signUpRequest.setPassword("password");
        signup(signUpRequest);
    }


    public void signup(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsernameOrEmail(signUpRequest.getUsername(), signUpRequest.getEmail())) {
            throw new UserAlreadyExistException();
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(UserNotFoundException::new);

        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);
        System.out.println(userRepository.findAll());
    }


    public String login(LoginRequest loginRequest) {
        System.out.println("loginRequest = [" + loginRequest + "]");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }


//    public void callPasswordReset(String email) {
//        User user = userRepository.findByEmail(email);
//
//        if (user != null) {
//            String token = ResetTokenGenerator.nextPassword();
//            user.setResetToken(token);
//            user.setResetTimestamp(new Date());
//
//            emailSender.sendResetToken(email, token);
//            userRepository.save(user);
//        }
//    }
//
//
//    public void resetPassword(String resetToken, String newPassword) {
//        if (resetToken.isEmpty()) {
//            return;
//        }
//
//        User user = userRepository.findByResetToken(resetToken);
//
//        if (user != null) {
//            user.setResetToken("");
//            user.setPassword(newPassword);
//            userRepository.save(user);
//        }
//    }
}
