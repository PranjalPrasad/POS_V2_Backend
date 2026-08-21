package com.POS.dto.requestDto;

public class LoginRequestDto {

    private String email;      // NOTE: frontend field naam "email" hai, but value mobile number hoti hai
    private String password;
    private boolean rememberMe;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String email, String password, boolean rememberMe) {
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isRememberMe() { return rememberMe; }
    public void setRememberMe(boolean rememberMe) { this.rememberMe = rememberMe; }
}