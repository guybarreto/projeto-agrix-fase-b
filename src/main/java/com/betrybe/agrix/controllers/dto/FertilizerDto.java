package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition
) {

  public FertilizerDto(Fertilizer fertilizer) {
    this(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  public static FertilizerDto fertilizerToFertilizerDto(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer);
  }
}