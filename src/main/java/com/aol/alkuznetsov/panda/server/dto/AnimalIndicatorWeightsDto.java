package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalIndicatorWeightsDto {
  
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
