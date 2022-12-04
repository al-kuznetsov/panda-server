package com.aol.alkuznetsov.panda.server.model;

import lombok.Data;

/** Утилитарный класс для хранения расчета глобальных критериев */
@Data
public class AnimalCriteriaContainer {

  private final Animal animal;
  private AnimalIndicatorsNumeric animalIndicatorsNumericPreNormalize;
  private AnimalIndicatorsNumeric animalIndicatorsNumericPostNormalize;
  private AnimalIndicatorsNumeric animalIndicatorsNumericPostWeighing;
  private Double criteria;
}
