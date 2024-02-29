package com.betrybe.agrix.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handle farm not found exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException(FarmNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  /**
   * Handle crop not found exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

}