package com.POS.dto.requestDto;


public class LoginRequestDto {

    private String mobileNumber;
    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String mobileNumber, String password) {
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
