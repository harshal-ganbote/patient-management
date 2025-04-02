package com.pm.authservice.service;

import com.pm.authservice.model.User;

import java.util.Optional;

public interface IUserService {
  Optional<User> findByEmail(String email);
}
