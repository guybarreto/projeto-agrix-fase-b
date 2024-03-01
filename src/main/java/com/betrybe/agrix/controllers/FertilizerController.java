package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Fertilizer createFertilizer(@RequestBody Fertilizer fertilizer) {
    return fertilizerService.createFertilizer(fertilizer);
  }

  @GetMapping
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();

    List <FertilizerDto> fertilizerDtoList = fertilizers.stream()
        .map(FertilizerDto::fertilizerToFertilizerDto)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDtoList);
  }

  @GetMapping("/{fertilizerId}")
  public ResponseEntity<Fertilizer> getFertilizerById(@PathVariable Long fertilizerId) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    return ResponseEntity.ok(fertilizer);
  }
}