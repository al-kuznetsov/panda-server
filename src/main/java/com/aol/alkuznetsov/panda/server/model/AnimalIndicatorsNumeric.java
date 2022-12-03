package com.aol.alkuznetsov.panda.server.model;

import lombok.Builder;
import lombok.Data;

/** A utility class representing all local criteria in numeric way as Double values */
@Data
@Builder
public class AnimalIndicatorsNumeric {
  private Long id;
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
