package com.aol.alkuznetsov.panda.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Утилитарный класс для расчета глобальных критериев */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalCriteriaContainer {

  private Animal animal;
  private AnimalIndicators animalIndicatorsPostNormalize;
  private AnimalIndicators animalIndicatorsPostWeighing;
  private Double criteria;
}
