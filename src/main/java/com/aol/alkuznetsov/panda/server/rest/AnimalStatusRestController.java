package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.AnimalStatusDto;
import com.aol.alkuznetsov.panda.server.service.AnimalStatusService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}/animal-statuses")
@RequiredArgsConstructor
public class AnimalStatusRestController {
  private final AnimalStatusService animalStatusService;

  @GetMapping
  public List<AnimalStatusDto> findAll() {
    return animalStatusService.findAll();
  }
}
