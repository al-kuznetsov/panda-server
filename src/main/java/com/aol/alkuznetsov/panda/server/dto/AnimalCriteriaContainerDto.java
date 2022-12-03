package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalCriteriaContainerDto {

  private AnimalDto animal;
  private AnimalIndicatorsNumericDto animalIndicatorsNumericPreNormalize;
  private AnimalIndicatorsNumericDto animalIndicatorsNumericPostNormalize;
  private AnimalIndicatorsNumericDto animalIndicatorsNumericPostWeighing;
  private Double criteria;
}
