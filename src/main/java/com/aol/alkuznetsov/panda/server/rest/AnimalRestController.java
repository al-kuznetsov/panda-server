package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.service.AnimalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}")
@RequiredArgsConstructor
public class AnimalRestController {
  private final AnimalService animalService;

  @GetMapping("/animals")
  public List<AnimalDto> findAll() {
    return animalService.findAll();
  }
}
