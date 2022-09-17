package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.mapper.AnimalMapper;
import com.aol.alkuznetsov.panda.server.repository.AnimalRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimalService {
  private final AnimalRepository animalRepository;
  private final AnimalMapper animalMapper;

  @Transactional(readOnly = true)
  public List<AnimalDto> findAll() {
    return animalRepository.findAll().stream()
        .map(animalMapper::toDto)
        .collect(Collectors.toList());
  }
}
