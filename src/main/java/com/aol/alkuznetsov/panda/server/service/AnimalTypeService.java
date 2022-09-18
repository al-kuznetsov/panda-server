package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.AnimalTypeDto;
import com.aol.alkuznetsov.panda.server.mapper.AnimalTypeMapper;
import com.aol.alkuznetsov.panda.server.repository.AnimalTypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimalTypeService {
  private final AnimalTypeRepository animalTypeRepository;
  private final AnimalTypeMapper animalTypeMapper;

  @Transactional(readOnly = true)
  public List<AnimalTypeDto> findAll() {
    return animalTypeRepository.findAll().stream()
        .map(animalTypeMapper::toDto)
        .collect(Collectors.toList());
  }
}
