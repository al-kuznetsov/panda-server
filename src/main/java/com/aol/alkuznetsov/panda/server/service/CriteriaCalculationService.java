package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.config.AnimalIndicatorWeights;
import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.AnimalCriteriaContainer;
import com.aol.alkuznetsov.panda.server.model.AnimalIndicators;
import com.aol.alkuznetsov.panda.server.model.AnimalIndicatorsNumeric;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Настоящий класс выполняет расчет глобальных криетриев для ранжирования
 * набора животных по заданному набору локальных критериев (индикаторов)
 * для каждого животного методом векторной оптимизации с применением
 * аддитивной свертки.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CriteriaCalculationService {

  private static final int MAX_NUMBER_OF_ANIMALS = 5;

  // Matrix column names
  private static final String AGE = AnimalIndicators.Fields.age;
  private static final String IS_INFANT = AnimalIndicators.Fields.isInfant;
  private static final String CONSCIOUSNESS_LEVEL = AnimalIndicators.Fields.consciousnessLevel;
  private static final String HEIGHT = AnimalIndicators.Fields.height;
  private static final String BREATHING_RATE = AnimalIndicators.Fields.breathingRate;
  private static final String HEART_RATE = AnimalIndicators.Fields.heartRate;
  private static final String BLEEDING_LEVEL = AnimalIndicators.Fields.bleedingLevel;
  private static final String BODY_TEMPERATURE = AnimalIndicators.Fields.bodyTemperature;
  private static final String SEVERE_DAMAGE_COUNT = AnimalIndicators.Fields.severeDamageCount;
  private static final String MILD_DAMAGE_COUNT = AnimalIndicators.Fields.mildDamageCount;
  private static final String MOBILITY_LOSS_LEVEL = AnimalIndicators.Fields.mobilityLossLevel;
  private static final String APPETITE_LEVEL = AnimalIndicators.Fields.appetiteLevel;
  private static final String HAS_SYMPTOMS = AnimalIndicators.Fields.hasSymptoms;
  private static final String IS_PREGNANT = AnimalIndicators.Fields.isPregnant;
  private static final String AGGRESSION_LEVEL = AnimalIndicators.Fields.aggressionLevel;

  private static final String CRITERIA = "criteria";

  private final AnimalIndicatorWeights animalIndicatorWeights;

  private Map<String, Map<Long, Double>> matrix;

  public List<AnimalCriteriaContainer> getCalculatedCriteria(List<Animal> animals) {
    Assert.notEmpty(animals, "Animal list must not be null nor empty");
    Assert.isTrue(animals.size() <= MAX_NUMBER_OF_ANIMALS,
        "Animal list contains too many elements");

    // 1. Build the matrix representation and the container list.
    matrix = new HashMap<>();
    matrix.put(AGE, new HashMap<>());
    matrix.put(IS_INFANT, new HashMap<>());
    matrix.put(CONSCIOUSNESS_LEVEL, new HashMap<>());
    matrix.put(HEIGHT, new HashMap<>());
    matrix.put(BREATHING_RATE, new HashMap<>());
    matrix.put(HEART_RATE, new HashMap<>());
    matrix.put(BLEEDING_LEVEL, new HashMap<>());
    matrix.put(BODY_TEMPERATURE, new HashMap<>());
    matrix.put(SEVERE_DAMAGE_COUNT, new HashMap<>());
    matrix.put(MILD_DAMAGE_COUNT, new HashMap<>());
    matrix.put(MOBILITY_LOSS_LEVEL, new HashMap<>());
    matrix.put(APPETITE_LEVEL, new HashMap<>());
    matrix.put(HAS_SYMPTOMS, new HashMap<>());
    matrix.put(IS_PREGNANT, new HashMap<>());
    matrix.put(AGGRESSION_LEVEL, new HashMap<>());
    matrix.put(CRITERIA, new HashMap<>());

    List<AnimalCriteriaContainer> containers = new ArrayList<>(animals.size());

    // 2. Population
    animals.forEach(tempAnimal -> {
      Long id = tempAnimal.getId();
      AnimalIndicators indicators = tempAnimal.getIndicators();
      log.debug("Extracting animal indicators: age = {}", indicators.getAge());
      matrix.get(AGE).put(id, indicators.getAge().doubleValue());
      matrix.get(IS_INFANT).put(id, translateBooleanIntoDouble(indicators.getIsInfant()));
      matrix.get(CONSCIOUSNESS_LEVEL).put(
          id, indicators.getConsciousnessLevel().getLevel().doubleValue());
      matrix.get(HEIGHT).put(id, indicators.getHeight());
      matrix.get(BREATHING_RATE).put(id, indicators.getBreathingRate().doubleValue());
      matrix.get(HEART_RATE).put(id, indicators.getHeartRate().doubleValue());
      matrix.get(BLEEDING_LEVEL).put(
          id, indicators.getBleedingLevel().getLevel().doubleValue());
      matrix.get(BODY_TEMPERATURE).put(id, indicators.getBodyTemperature());
      matrix.get(SEVERE_DAMAGE_COUNT).put(id, indicators.getSevereDamageCount().doubleValue());
      matrix.get(MILD_DAMAGE_COUNT).put(id, indicators.getMildDamageCount().doubleValue());
      matrix.get(MOBILITY_LOSS_LEVEL).put(id,
          indicators.getMobilityLossLevel().getLevel().doubleValue());
      matrix.get(APPETITE_LEVEL).put(id, indicators.getAppetiteLevel().getLevel().doubleValue());
      matrix.get(HAS_SYMPTOMS).put(id, translateBooleanIntoDouble(indicators.getHasSymptoms()));
      matrix.get(IS_PREGNANT).put(id, translateBooleanIntoDouble(indicators.getIsPregnant()));
      matrix.get(AGGRESSION_LEVEL).put(id,
          indicators.getAggressionLevel().getLevel().doubleValue());
      matrix.get(CRITERIA).put(id, 88.0);

      // Write pre-normalize matrix into container.
      AnimalIndicatorsNumeric indicatorsPreNormalise = AnimalIndicatorsNumeric.builder()
          .age(getMatrixValue(AGE, id))
          .isInfant(getMatrixValue(IS_INFANT, id))
          .consciousnessLevel(getMatrixValue(CONSCIOUSNESS_LEVEL, id))
          .height(getMatrixValue(HEIGHT, id))
          .breathingRate(getMatrixValue(BREATHING_RATE, id))
          .heartRate(getMatrixValue(HEIGHT, id))
          .bleedingLevel(getMatrixValue(BLEEDING_LEVEL, id))
          .bodyTemperature(getMatrixValue(BODY_TEMPERATURE, id))
          .severeDamageCount(getMatrixValue(SEVERE_DAMAGE_COUNT, id))
          .mildDamageCount(getMatrixValue(MILD_DAMAGE_COUNT, id))
          .mobilityLossLevel(getMatrixValue(MOBILITY_LOSS_LEVEL, id))
          .appetiteLevel(getMatrixValue(APPETITE_LEVEL, id))
          .hasSymptoms(getMatrixValue(HAS_SYMPTOMS, id))
          .isPregnant(getMatrixValue(IS_PREGNANT, id))
          .aggressionLevel(getMatrixValue(AGGRESSION_LEVEL, id))
          .build();

      containers.add(
          new AnimalCriteriaContainer(
              tempAnimal,
              indicatorsPreNormalise,
              indicatorsPreNormalise,
              indicatorsPreNormalise, 88.0));
    });

    // 3. Normalization.
    // TODO Implement normalization.

    // 4. Do the additive convolution.
    // TODO Implement the convolution.

    // 5. Return the result container.
    return containers;
  }

  private double translateBooleanIntoDouble(Boolean booleanObj) {
    if (booleanObj == null || Boolean.FALSE.equals(booleanObj)) {
      return 0.0;
    }
    return 1.0;
  }

  private double getMatrixValue(String fieldName, Long animalId) {
    return this.matrix.get(fieldName).get(animalId);
  }

  private double putMatrixValue(String fieldName, Long animalId, Double value) {
    return this.matrix.get(fieldName).put(animalId, value);
  }
}
