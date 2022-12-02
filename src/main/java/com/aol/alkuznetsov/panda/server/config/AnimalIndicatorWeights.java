package com.aol.alkuznetsov.panda.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Весовые коэффициенты, отражающие степень важности каждого
 * локального критерия.
 */
@Component
@ConfigurationProperties(prefix = "vars.animal-indicator-weights")
public class AnimalIndicatorWeights {

  private Double age;
  private Double isInfant;
  private Double consciousnessLevel;
  private Double height;
  private Double breathingRate;
  private Double heartRate;
  private Double bleedingLevel;
  private Double bodyTemperature;
  private Double severeDamageCount;
  private Double mildDamageCount;
  private Double mobilityLossLevel;
  private Double appetiteLevel;
  private Double hasSymptoms;
  private Double isPregnant;
  private Double aggressionLevel;
}
