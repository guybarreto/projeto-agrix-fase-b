package com.betrybe.agrix.controllers.exception;

/**
 * The type Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Fertilizer not found exception.
   *
   * @param message the message
   */
  public FertilizerNotFoundException(String message) {
    super("Fertilizante não encontrado!");
  }
}