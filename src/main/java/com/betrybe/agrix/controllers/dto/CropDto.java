package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId
) {

  /**
   * Instantiates a new Crop dto.
   *
   * @param crop the crop
   */
  public CropDto(Crop crop) {
    this(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    );
  }

  /**
   * Crop to crop dto crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto cropToCropDto(Crop crop) {
    return new CropDto(crop);
  }
}