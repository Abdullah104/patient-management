package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDto;
import com.pm.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDto loginRequestDto) {
        return userService
                .findByEmail(loginRequestDto.email())
                .filter(user -> passwordEncoder.matches(loginRequestDto.password(), user.getPassword()))
                .map(user -> jwtUtil.generateToken(user.getEmail(), user.getRole()));
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);

            return true;
        } catch (JwtException exception) {
            log.info(exception.getMessage());

            return false;
        }
    }
}
