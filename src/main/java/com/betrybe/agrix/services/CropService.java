package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.controllers.exception.CropNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param farmService       the farm service
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(
      CropRepository cropRepository,
      FarmService farmService, FertilizerService fertilizerService

  ) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
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
   * Gets crops by harvest date.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by harvest date
   */
  public List<Crop> getCropsByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
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

  /**
   * Associate fertilizer with crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  public void associateFertilizerWithCrop(Long cropId, Long fertilizerId) {
    Crop crop = getCropById(cropId);
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);

    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   */
  public List<FertilizerDto> getFertilizersByCropId(Long cropId) {
    Crop crop = getCropById(cropId);

    return crop.getFertilizers().stream()
        .map(FertilizerDto::fertilizerToFertilizerDto)
        .collect(Collectors.toList());
  }
}
