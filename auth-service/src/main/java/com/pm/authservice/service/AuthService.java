package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {
  private final IUserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public AuthService(IUserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Optional<String> login(LoginRequestDTO loginRequestDTO) {

    return userService.findByEmail(loginRequestDTO.getEmail())
            .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
            .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));
  }

  @Override
  public boolean validateToken(String token) {
    try {
     jwtUtil.validateToken(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
