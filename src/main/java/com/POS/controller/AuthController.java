package com.POS.controller;

import com.POS.dto.requestDto.LoginRequestDto;
import com.POS.dto.resposneDto.LoginResponseDto;
import com.POS.entity.User;
import com.POS.repository.UserRepository;
import com.POS.service.AuthService;
import com.POS.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto response = authService.login(requestDto);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> profile(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        Map<String, Object> result = new HashMap<>();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            result.put("success", false);
            result.put("message", "Missing or invalid Authorization header. Use: Bearer <token>");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            result.put("success", false);
            result.put("message", "Invalid or expired token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }

        String mobileNumber = jwtUtil.extractMobileNumber(token);
        User user = userRepository.findByMobileNumber(mobileNumber);

        if (user == null) {
            result.put("success", false);
            result.put("message", "User no longer exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        result.put("success", true);
        result.put("mobileNumber", user.getMobileNumber());
        result.put("name", user.getName());
        return ResponseEntity.ok(result);
    }
}
