package com.aol.alkuznetsov.panda.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * This service is used to run helper startup services, e.g.: Database population, etc. It fires up
 * at startup time if "init" profile is used.
 */
@Log4j2
@Component
@Profile({"init"})
@RequiredArgsConstructor
public class StartupService implements ApplicationRunner {
  private final DatabasePopulationService databasePopulationService;

  @Override
  public void run(ApplicationArguments args) {
    log.info("StartupService runs");
    databasePopulationService.populate();
    log.info("StartupService finished");
  }
}
