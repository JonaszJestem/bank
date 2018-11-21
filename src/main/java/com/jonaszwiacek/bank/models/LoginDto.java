package com.jonaszwiacek.bank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LoginDto {
    private String login;
    private String password;

    public LoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginDto() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
