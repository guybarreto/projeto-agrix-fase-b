package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer dto.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition
) {

  /**
   * Instantiates a new Fertilizer dto.
   *
   * @param fertilizer the fertilizer
   */
  public FertilizerDto(Fertilizer fertilizer) {
    this(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  /**
   * Fertilizer to fertilizer dto fertilizer dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer dto
   */
  public static FertilizerDto fertilizerToFertilizerDto(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer);
  }
}