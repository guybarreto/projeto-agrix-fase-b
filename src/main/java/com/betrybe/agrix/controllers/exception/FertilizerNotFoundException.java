package com.betrybe.agrix.controllers.exception;

public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException(String message) {
    super("Fertilizante não encontrado!");
  }
}