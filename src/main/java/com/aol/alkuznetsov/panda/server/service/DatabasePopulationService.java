package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.Spot;
import com.aol.alkuznetsov.panda.server.repository.AnimalRepository;
import com.aol.alkuznetsov.panda.server.repository.SpotRepository;
import com.aol.alkuznetsov.panda.server.util.DebugUtils;
import java.util.List;
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
  private final AnimalRepository animalRepository;
  private final SpotRepository spotRepository;

  @Value("${vars.startup.init-sample-data}")
  private Boolean isInitSampleData;

  @Transactional
  public void populate() {
    log.info("Populating Database with data at startup");

    if (Boolean.TRUE.equals(isInitSampleData)) {

      log.info("Saving sample data to the database");

      List<Animal> animals = persistentDataProviderService.newListOfAnimals();
      List<Animal> savedAnimals = animalRepository.saveAll(animals);
      log.debug(DebugUtils.getMessageWithAnimalsList("Animals saved to Database: ", savedAnimals));

      List<Spot> spots = persistentDataProviderService.newListOfSpots();
      spotRepository.saveAll(spots);

      persistentDataProviderService.newListOfAddressType(true);
      persistentDataProviderService.newListOfCountries(true);
      persistentDataProviderService.newListOfRegionTypes(true);
      persistentDataProviderService.newListOfRegions(true);
      persistentDataProviderService.newListOfLocalityTypes(true);
      persistentDataProviderService.newListOfLocalities(true);
    }
  }
}
