package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.exception.DataNotFoundException;
import com.aol.alkuznetsov.panda.server.mapper.AnimalMapper;
import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.repository.AnimalRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AnimalService {

  public static final String NOT_FOUND_BY_ID_MSG = "Animal not found in Database for id: ";
  private final AnimalRepository animalRepository;
  private final AnimalMapper animalMapper;
  private final CriteriaCalculationService criteriaCalculationService;

  @Transactional(readOnly = true)
  public Page<AnimalDto> findAll(Pageable pageable) {
    log.debug("Retrieving a Page of all animals as AnimalDtos");
    return animalRepository.findAll(pageable).map(animalMapper::toDto);
  }

  @Transactional(readOnly = true)
  public Page<AnimalDto> findAllByTypeCode(String code, Pageable pageable) {
    log.debug("Retrieving a Page of animals with animal type code: {}", code);
    return animalRepository.findAllByTypeCode(code, pageable).map(animalMapper::toDto);
  }

  @Transactional(readOnly = true)
  public Page<AnimalDto> findAllByNameOrDescriptionContainingIgnoreCase(
      String searchKey, Pageable pageable) {
    log.debug(
        "Retrieving a Page of animals with given searchKey in name or description: {}", searchKey);
    return animalRepository
        .findAllByNameOrDescriptionContainingIgnoreCase(searchKey, searchKey, pageable)
        .map(animalMapper::toDto);
  }

  @Transactional(readOnly = true)
  public AnimalDto findById(Long id) {
    log.debug("Retrieving animal as AnimalDto by Id {}", id);
    Animal animal = animalRepository.findById(id).orElse(null);
    return animalMapper.toDto(animal);
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
      throw new DataNotFoundException(NOT_FOUND_BY_ID_MSG + id);
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
      throw new DataNotFoundException(NOT_FOUND_BY_ID_MSG + id);
    }
    animalRepository.deleteById(id);
    log.debug("Deleted Animal with id {}", id);
  }

  @Transactional
  public List<Pair<Animal, Double>> calculateCriteriaVectorForAnimalIds(List<Long> ids) {
    log.debug("Retrieving a vector of animal-criteria pairs for a list of ids: {}", ids);
    List<Animal> animals = animalRepository.findAllByIdList(ids);
    return criteriaCalculationService.calculateCriteria(animals);
  }
}
