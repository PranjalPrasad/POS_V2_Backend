package com.POS.service.serviceImpl;

import com.POS.dto.requestDto.LoginRequestDto;
import com.POS.dto.responseDto.LoginResponseDto;
import com.POS.entity.User;
import com.POS.repository.UserRepository;
import com.POS.service.AuthService;
import com.POS.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private static final String MOBILE_NUMBER_REGEX = "^[0-9]{10}$";

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {

        String mobileNumber = requestDto.getEmail(); // field naam "email" hai, value mobile number
        String password = requestDto.getPassword();

        if (mobileNumber == null || mobileNumber.trim().isEmpty()) {
            return new LoginResponseDto(false, "Mobile number is required", null, null, null);
        }

        if (password == null || password.trim().isEmpty()) {
            return new LoginResponseDto(false, "Password is required", null, null, null);
        }

        if (!mobileNumber.matches(MOBILE_NUMBER_REGEX)) {
            return new LoginResponseDto(false, "Mobile number must be exactly 10 digits", null, null, null);
        }

        User user = userRepository.findByMobileNumber(mobileNumber);

        if (user == null) {
            return new LoginResponseDto(false, "User not found with this mobile number", null, null, null);
        }

        if (!user.getPassword().equals(password)) {
            return new LoginResponseDto(false, "Invalid password", null, null, null);
        }

        // rememberMe true -> long-lived token, false -> normal token
        String token = jwtUtil.generateToken(user.getMobileNumber(), requestDto.isRememberMe());

        return new LoginResponseDto(true, "Login successful", token, user.getMobileNumber(), user.getName());
    }
}