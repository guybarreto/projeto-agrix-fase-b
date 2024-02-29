package com.betrybe.agrix.controllers.exception;

/**
 * The type Farm not found exception.
 */
public class FarmNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Farm not found exception.
   *
   * @param message the message
   */
  public FarmNotFoundException(String message) {
    super("Fazenda não encontrada!");
  }
}

