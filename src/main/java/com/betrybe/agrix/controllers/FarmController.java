package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   * @param cropService the crop service
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create farm response entity.
   *
   * @param farm the farm
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody Farm farm) {
    Farm newFarm = farmService.createFarm(farm);
    FarmDto newFarmDto = FarmDto.farmToFarmDto(newFarm);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarmDto);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();

    List<FarmDto> farmDtoList = farms.stream()
        .map(FarmDto::farmToFarmDto)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(farmDtoList);
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);
    FarmDto farmDto = FarmDto.farmToFarmDto(farm);
    return ResponseEntity.status(HttpStatus.OK).body(farmDto);
  }

  /**
   * Create crop response entity.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the response entity
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody Crop crop
  ) {
    Crop newCrop = cropService.createCrop(farmId, crop);
    CropDto newCropDto = CropDto.cropToCropDto(newCrop);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCropDto);
  }

  /**
   * Gets all crops by farm id.
   *
   * @param farmId the farm id
   * @return the all crops by farm id
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getAllCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = cropService.getAllCropsByFarmId(farmId);

    List<CropDto> cropDtoList = crops.stream()
        .map(CropDto::cropToCropDto)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropDtoList);
  }
}
