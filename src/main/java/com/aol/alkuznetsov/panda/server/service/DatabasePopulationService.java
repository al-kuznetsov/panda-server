package com.aol.alkuznetsov.panda.server.service;

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
  private final PersistentDataProviderService persistentDataProviderService;

  @Value("${vars.startup.init-sample-data}")
  private Boolean isInitSampleData;

  @Transactional
  public void populate() {
    log.info("Populating Database with data at startup");

    if (Boolean.TRUE.equals(isInitSampleData)) {

      log.info("Saving sample data to the database");

      persistentDataProviderService.newListOfCountries(true);
      persistentDataProviderService.newListOfRegionTypes(true);
      persistentDataProviderService.newListOfRegions(true);
      persistentDataProviderService.newListOfLocalityTypes(true);
      persistentDataProviderService.newListOfLocalities(true);
      persistentDataProviderService.newListOfAddressType(true);
      persistentDataProviderService.newListOfAnimalType(true);
      persistentDataProviderService.newListOfBreed(true);
      persistentDataProviderService.newListOfAnimalStatus(true);
      persistentDataProviderService.newListOfAnimals(true);

      persistentDataProviderService.newListOfSpots(true);

      persistentDataProviderService.newListOfUsers(true);
    }
  }
}
