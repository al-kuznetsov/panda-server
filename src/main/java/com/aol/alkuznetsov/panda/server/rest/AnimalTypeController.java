package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.AnimalTypeDto;
import com.aol.alkuznetsov.panda.server.service.AnimalTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}")
@RequiredArgsConstructor
public class AnimalTypeController {
  private final AnimalTypeService animalTypeService;

  @GetMapping("/animal-types")
  public List<AnimalTypeDto> findAll() {
    return animalTypeService.findAll();
  }
}
