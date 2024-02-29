package com.betrybe.agrix.controllers.exception;

/**
 * The type Crop not found exception.
 */
public class CropNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Crop not found exception.
   *
   * @param message the message
   */
  public CropNotFoundException(String message) {
    super("Plantação não encontrada!");
  }
}

