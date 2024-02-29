package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.exception.CropNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository the crop repository
   * @param farmService    the farm service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
  }

  /**
   * Create crop crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   */
  public Crop createCrop(Long farmId, Crop crop) {
    Farm farm = farmService.getFarmById(farmId);
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * Gets all crops by farm id.
   *
   * @param farmId the farm id
   * @return the all crops by farm id
   */
  public List<Crop> getAllCropsByFarmId(Long farmId) {
    Farm farm = farmService.getFarmById(farmId);
    return farm.getCrops();
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  public Crop getCropById(Long id) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);
    return optionalCrop.orElseThrow(() -> new CropNotFoundException("Plantação não encontrada!"));
  }
}
