package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service is used to populate Database with sample data. Meant to be used at startup time. If
 * "vars.startup.init-sample-data" is {@code true} sample data is included, otherwise only the
 * reference data is saved to database.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DatabasePopulationService {
  private final AnimalRepository animalRepository;

  @Value("${vars.startup.init-sample-data}")
  private Boolean isInitSampleData;

  @Transactional
  public void populate() {
    // TODO Call sample data providers here and save entities via JPA repositories
    log.info("Populating Database with data at startup");
  }
}
