package com.pm.patientservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();

    e.getBindingResult().getFieldErrors().forEach((error) -> errors.put(error.getField(), error.getDefaultMessage()));

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {

    logger.warn("Email already exists {}", e.getMessage());
    Map<String, String> errors = new HashMap<>();
    errors.put("message", e.getMessage());
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PatientNotFoundException.class)
  public ResponseEntity<Map<String, String>> handlePatientNotFoundException(PatientNotFoundException e) {

    logger.warn("Patient not found {}", e.getMessage());
    Map<String, String> errors = new HashMap<>();
    errors.put("message", e.getMessage());
    return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
  }
}
