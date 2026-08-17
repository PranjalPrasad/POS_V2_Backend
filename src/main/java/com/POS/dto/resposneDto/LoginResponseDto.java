package com.POS.dto.resposneDto;

public class LoginResponseDto {

    private boolean success;
    private String message;
    private String token;
    private String mobileNumber;
    private String name;

    public LoginResponseDto() {
    }

    public LoginResponseDto(boolean success, String message, String token, String mobileNumber, String name) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.mobileNumber = mobileNumber;
        this.name = name;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
