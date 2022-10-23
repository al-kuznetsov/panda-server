package com.aol.alkuznetsov.panda.server.rest;

import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.service.AnimalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${vars.api.version-url}/animals")
@RequiredArgsConstructor
public class AnimalRestController {
  private final AnimalService animalService;

  @GetMapping
  public List<AnimalDto> findAll() {
    return animalService.findAll();
  }

  @GetMapping("/{id}")
  public AnimalDto find(@PathVariable Long id) {
    return animalService.findById(id);
  }

  @PostMapping
  public AnimalDto create(@RequestBody AnimalDto animalDto) {
    return animalService.create(animalDto);
  }

  @PutMapping("/{id}")
  public AnimalDto update(@PathVariable Long id, @RequestBody AnimalDto animalDto) {
    return animalService.update(id, animalDto);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    animalService.deleteById(id);
  }
}
