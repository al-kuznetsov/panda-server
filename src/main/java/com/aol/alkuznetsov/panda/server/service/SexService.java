package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.SexDto;
import com.aol.alkuznetsov.panda.server.mapper.SexMapper;
import com.aol.alkuznetsov.panda.server.repository.SexRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SexService {
  private final SexRepository sexRepository;
  private final SexMapper sexMapper;

  @Transactional(readOnly = true)
  public List<SexDto> findAll() {
    return sexRepository.findAll().stream()
        .map(sexMapper::toDto)
        .collect(Collectors.toList());
  }
}
