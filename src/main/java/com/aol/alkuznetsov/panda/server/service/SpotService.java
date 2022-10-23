package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.SpotDto;
import com.aol.alkuznetsov.panda.server.mapper.SpotMapper;
import com.aol.alkuznetsov.panda.server.repository.SpotRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpotService {

  private final SpotRepository spotRepository;
  private final SpotMapper spotMapper;

  @Transactional(readOnly = true)
  public List<SpotDto> findAllSpots() {
    return spotRepository.findAll().stream().map(spotMapper::toDto).collect(Collectors.toList());
  }
}
