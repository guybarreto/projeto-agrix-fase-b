package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Instantiates a new Farm dto.
   *
   * @param farm the farm
   */
  public FarmDto(Farm farm) {
    this(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * Farm to farm dto farm dto.
   *
   * @param farm the farm
   * @return the farm dto
   */
  public static FarmDto farmToFarmDto(Farm farm) {
    return new FarmDto(farm);
  }
}