package com.jonaszwiacek.bank.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    private String login;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String resetToken;
    private Date resetTimestamp;

//    @OneToOne
//    @JoinColumn(name = "fk_passwordreset")
//    private PasswordResetsRequest passwordResetsRequest;


    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setResetTimestamp(Date resetTimestamp) {
        this.resetTimestamp = resetTimestamp;
    }


    public String getResetToken() {
        return resetToken;
    }


    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }


    public Date getResetTimestamp() {
        return resetTimestamp;
    }
}

