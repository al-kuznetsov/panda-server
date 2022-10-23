package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.exception.DataNotFoundException;
import com.aol.alkuznetsov.panda.server.mapper.AnimalMapper;
import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.repository.AnimalRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AnimalService {
  private final AnimalRepository animalRepository;
  private final AnimalMapper animalMapper;

  @Transactional(readOnly = true)
  public List<AnimalDto> findAll() {
    log.debug("Retrieving the list of all animals as AnimalDtos");
    return animalRepository.findAll().stream()
        .map(animalMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public AnimalDto create(AnimalDto animalDto) {
    log.debug("Creating new Animal based on AnimalDto {}", animalDto);
    Animal savedAnimal = animalRepository.save(animalMapper.fromDto(animalDto));
    log.debug("Saved new Animal {}", savedAnimal);
    return animalMapper.toDto(savedAnimal);
  }

  @Transactional
  public AnimalDto update(Long id, AnimalDto animalDto) {
    log.debug("Updating Animal with id {} based on AnimalDto {}", id, animalDto);
    if (!animalRepository.existsById(id)) {
      throw new DataNotFoundException("Animal not found in Database for id: " + id);
    }
    Animal animal = animalMapper.fromDto(animalDto);
    animal.setId(id);
    Animal updatedAnimal = animalRepository.save(animal);
    log.debug("Updated Animal {}", updatedAnimal);
    return animalMapper.toDto(updatedAnimal);
  }

  @Transactional
  public void deleteById(Long id) {
    log.debug("Deleting Animal with id {}", id);
    if (!animalRepository.existsById(id)) {
      throw new DataNotFoundException("Animal not found in Database for id: " + id);
    }
    animalRepository.deleteById(id);
    log.debug("Deleted Animal with id {}", id);
  }
}
