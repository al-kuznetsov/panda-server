package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.dto.CountryDto;
import com.aol.alkuznetsov.panda.server.mapper.CountryMapper;
import com.aol.alkuznetsov.panda.server.repository.CountryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryService {

  private final CountryRepository countryRepository;
  private final CountryMapper countryMapper;

  @Transactional(readOnly = true)
  public List<CountryDto> findAll() {
    return countryRepository.findAll().stream()
        .map(countryMapper::toDto)
        .collect(Collectors.toList());
  }
}
