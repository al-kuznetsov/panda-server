package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalIndicatorsDto {
  private Long id;
  private Integer age;
  private Boolean isInfant;
  private String consciousnessLevel;
  private Double height;
  private Integer breathingRate;
  private Integer heartRate;
  private String bleedingLevel;
  private Double bodyTemperature;
  private Integer severeDamageCount;
  private Integer mildDamageCount;
  private String mobilityLossLevel;
  private String appetiteLevel;
  private Boolean hasSymptoms;
  private Boolean isPregnant;
  private String aggressionLevel;
}
