package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.AnimalStatusDto;
import com.aol.alkuznetsov.panda.server.mapper.AnimalStatusMapper;
import com.aol.alkuznetsov.panda.server.repository.AnimalStatusRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimalStatusService {
  private final AnimalStatusRepository animalStatusRepository;
  private final AnimalStatusMapper animalStatusMapper;

  @Transactional(readOnly = true)
  public List<AnimalStatusDto> findAll() {
    return animalStatusRepository.findAll().stream()
        .map(animalStatusMapper::toDto)
        .collect(Collectors.toList());
  }
}
