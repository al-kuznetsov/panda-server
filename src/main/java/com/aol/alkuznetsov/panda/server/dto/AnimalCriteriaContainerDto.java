package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalCriteriaContainerDto {

  private AnimalDto animal;
  private AnimalIndicatorsDto animalIndicatorsPostNormalize;
  private AnimalIndicatorsDto animalIndicatorsPostWeighing;
  private Double criteria;
}
