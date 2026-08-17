package com.POS.service;

import com.POS.dto.requestDto.LoginRequestDto;
import com.POS.dto.resposneDto.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto requestDto);
}
