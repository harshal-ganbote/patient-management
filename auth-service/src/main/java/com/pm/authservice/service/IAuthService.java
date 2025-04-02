package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDTO;

import java.util.Optional;

public interface IAuthService {

  Optional<String> login(LoginRequestDTO loginRequestDTO);
  boolean validateToken(String token);
}
