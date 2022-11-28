package com.aol.alkuznetsov.panda.server.dto;

import com.aol.alkuznetsov.panda.server.enums.AggressionLevel;
import com.aol.alkuznetsov.panda.server.enums.AppetiteLevel;
import com.aol.alkuznetsov.panda.server.enums.BleedingLevel;
import com.aol.alkuznetsov.panda.server.enums.ConsciousnessLevel;
import com.aol.alkuznetsov.panda.server.enums.MobilityLossLevel;
import javax.persistence.Column;
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
  private AppetiteLevel appetiteLevel;
  private Boolean hasSymptoms;
  private Boolean isPregnant;
  private String aggressionLevel;
}
