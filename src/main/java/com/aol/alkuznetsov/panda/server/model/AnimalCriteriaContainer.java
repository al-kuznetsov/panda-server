package com.aol.alkuznetsov.panda.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Утилитарный класс для хранения расчета глобальных критериев */
@Data
@AllArgsConstructor
public class AnimalCriteriaContainer {

  private Animal animal;
  private AnimalIndicatorsNumeric animalIndicatorsNumericPreNormalize;
  private AnimalIndicatorsNumeric animalIndicatorsNumericPostNormalize;
  private AnimalIndicatorsNumeric animalIndicatorsNumericPostWeighing;
  private Double criteria;
}
