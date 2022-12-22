package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.config.AnimalIndicatorWeights;
import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.AnimalCriteriaContainer;
import com.aol.alkuznetsov.panda.server.model.AnimalIndicators;
import com.aol.alkuznetsov.panda.server.model.AnimalIndicatorsNumeric;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

  // Константы-имена колонок матрицы
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

    // 1. Построение представления матрицы и контейнеров хранения векторов.
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

    // 2. Наполнение матрицы первичными данными.
    animals.forEach(tempAnimal -> {
      Long id = tempAnimal.getId();
      AnimalIndicators indicators = tempAnimal.getIndicators();
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
      matrix.get(CRITERIA).put(id, 0.0);

      // Запись ненормализованных значений в контейнеры.
      AnimalIndicatorsNumeric indicatorsPreNormalize = buildCurrentAnimalIndicatorsNumeric(id);

      AnimalCriteriaContainer container = new AnimalCriteriaContainer(tempAnimal);
      container.setAnimalIndicatorsNumericPreNormalize(indicatorsPreNormalize);

      containers.add(container);
    });
    
    // 3. Нормализация значений и запись в контейнеры.
    double maxHeight = getMatrixColumnMaxValue(HEIGHT);
    double rangeHeight = getMatrixColumnValueRange(HEIGHT);
    double minSevereDamageCount  = getMatrixColumnMinValue(SEVERE_DAMAGE_COUNT);
    double maxSevereDamageCount = getMatrixColumnMaxValue(SEVERE_DAMAGE_COUNT);
    double minMildDamageCount  = getMatrixColumnMinValue(MILD_DAMAGE_COUNT);
    double maxMildDamageCount = getMatrixColumnMaxValue(MILD_DAMAGE_COUNT);
    
    containers.forEach(tempContainer -> {
      Long id = tempContainer.getAnimal().getId();
      putMatrixValue(AGE, id,
          getNormalizedByOptimalReference(AGE, id, Double.valueOf(12)));
      putMatrixValue(HEIGHT, id,
          (maxHeight - getMatrixValue(HEIGHT, id))/ rangeHeight);
      putMatrixValue(BREATHING_RATE, id,
          getNormalizedByOptimalReference(BREATHING_RATE, id, Double.valueOf(20)));
      putMatrixValue(HEART_RATE, id,
          getNormalizedByOptimalReference(HEART_RATE, id, Double.valueOf(130)));
      putMatrixValue(BODY_TEMPERATURE, id,
          getNormalizedByOptimalReference(BODY_TEMPERATURE, id, Double.valueOf(38)));
      putMatrixValue(SEVERE_DAMAGE_COUNT, id,
          (getMatrixValue(SEVERE_DAMAGE_COUNT, id) - minSevereDamageCount)/maxSevereDamageCount);
      putMatrixValue(MILD_DAMAGE_COUNT, id,
          (getMatrixValue(MILD_DAMAGE_COUNT, id) - minMildDamageCount)/ maxMildDamageCount);

      // Запись нормализованных значений в контейнеры.
      AnimalIndicatorsNumeric indicatorsPostNormalize = buildCurrentAnimalIndicatorsNumeric(id);
      tempContainer.setAnimalIndicatorsNumericPostNormalize(indicatorsPostNormalize);
    });

    // 4. Выполнение аддитивной свертки.
    containers.forEach(tempContainer -> {
      Long id = tempContainer.getAnimal().getId();
      putMatrixValue(AGE, id, getMatrixValue(AGE, id) * animalIndicatorWeights.getAge());
      putMatrixValue(IS_INFANT, id,
          getMatrixValue(IS_INFANT, id) * animalIndicatorWeights.getIsInfant());
      putMatrixValue(CONSCIOUSNESS_LEVEL, id,
          getMatrixValue(CONSCIOUSNESS_LEVEL, id) * animalIndicatorWeights.getConsciousnessLevel());
      putMatrixValue(HEIGHT, id, getMatrixValue(HEIGHT, id) * animalIndicatorWeights.getHeight());
      putMatrixValue(BREATHING_RATE, id,
          getMatrixValue(BREATHING_RATE, id) * animalIndicatorWeights.getBreathingRate());
      putMatrixValue(HEART_RATE, id,
          getMatrixValue(HEART_RATE, id) * animalIndicatorWeights.getHeartRate());
      putMatrixValue(BLEEDING_LEVEL, id,
          getMatrixValue(BLEEDING_LEVEL, id) * animalIndicatorWeights.getBleedingLevel());
      putMatrixValue(BODY_TEMPERATURE, id,
          getMatrixValue(BODY_TEMPERATURE, id) * animalIndicatorWeights.getBodyTemperature());
      putMatrixValue(SEVERE_DAMAGE_COUNT, id,
          getMatrixValue(SEVERE_DAMAGE_COUNT, id) * animalIndicatorWeights.getSevereDamageCount());
      putMatrixValue(MILD_DAMAGE_COUNT, id,
          getMatrixValue(MILD_DAMAGE_COUNT, id) * animalIndicatorWeights.getMildDamageCount());
      putMatrixValue(MOBILITY_LOSS_LEVEL, id,
          getMatrixValue(MOBILITY_LOSS_LEVEL, id) * animalIndicatorWeights.getMobilityLossLevel());
      putMatrixValue(APPETITE_LEVEL, id,
          getMatrixValue(APPETITE_LEVEL, id) * animalIndicatorWeights.getAppetiteLevel());
      putMatrixValue(HAS_SYMPTOMS, id,
          getMatrixValue(HAS_SYMPTOMS, id) * animalIndicatorWeights.getHasSymptoms());
      putMatrixValue(IS_PREGNANT, id,
          getMatrixValue(IS_PREGNANT, id) * animalIndicatorWeights.getIsPregnant());
      putMatrixValue(AGGRESSION_LEVEL, id,
          getMatrixValue(AGGRESSION_LEVEL, id) * animalIndicatorWeights.getAggressionLevel());

      // Запись взвешенных значений в контейнеры.
      AnimalIndicatorsNumeric indicatorsPostWeighing = buildCurrentAnimalIndicatorsNumeric(id);
      tempContainer.setAnimalIndicatorsNumericPostWeighing(indicatorsPostWeighing);
      // Запись рассчитанных итоговых критериев в контейнеры.
      tempContainer.setCriteria(calculateTheCriteria(indicatorsPostWeighing));
    });

    // 5. Отсортировать контейнеры по убыванию итогового критерия.
    containers.sort(
        Comparator.comparing(AnimalCriteriaContainer::getCriteria, Comparator.reverseOrder()));

    // 6. Вернуть полностью просчитанные контейнеры.
    return containers;
  }

  private double getNormalizedByOptimalReference(String fieldName, Long id, Double referenceValue) {
    return Math.abs(getMatrixValue(fieldName, id) - referenceValue)
        / getMatrixColumnRange(fieldName);
  }

  private AnimalIndicatorsNumeric buildCurrentAnimalIndicatorsNumeric(Long id) {
    return AnimalIndicatorsNumeric.builder()
        .age(getMatrixValue(AGE, id))
        .isInfant(getMatrixValue(IS_INFANT, id))
        .consciousnessLevel(getMatrixValue(CONSCIOUSNESS_LEVEL, id))
        .height(getMatrixValue(HEIGHT, id))
        .breathingRate(getMatrixValue(BREATHING_RATE, id))
        .heartRate(getMatrixValue(HEART_RATE, id))
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
  }

  private double calculateTheCriteria(AnimalIndicatorsNumeric indicators) {
    return indicators.getAge() +
        indicators.getIsInfant() +
        indicators.getConsciousnessLevel() +
        indicators.getHeight() +
        indicators.getBreathingRate() +
        indicators.getHeartRate() +
        indicators.getBleedingLevel() +
        indicators.getBodyTemperature() +
        indicators.getSevereDamageCount() +
        indicators.getMildDamageCount() +
        indicators.getMobilityLossLevel() +
        indicators.getAppetiteLevel() +
        indicators.getHasSymptoms() +
        indicators.getIsPregnant() +
        indicators.getAggressionLevel();
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

  private double getMatrixColumnValueRange(String fieldName) {
    return getMatrixColumnMaxValue(fieldName) - getMatrixColumnMinValue(fieldName);
  }

  private double getMatrixColumnRange(String fieldName) {
    double overallRange;
    switch (fieldName) {
      case AGE:
        // диапазон 0 - 300 месяцев
        overallRange = 300.00;
        break;
      case BREATHING_RATE:
        // норма 12-40 вдохов в минуту
        // (в спокойном состоянии без нагрузки)
        // диапазон 0 - 60
        overallRange = 60.00;
        break;
      case HEART_RATE:
        // норма 70-130 ударов в минуту
        // диапазон 0 - 200
        overallRange = 200.00;
        break;
      case BODY_TEMPERATURE:
        // измеряемые значения в промежутке 35-43
        overallRange = 8.00;
        break;
      default:
        throw new IllegalArgumentException("Ошибочное наименование поля fieldName!");
    }
    return overallRange;
  }

  private double getMatrixColumnMaxValue(String fieldName) {
    return this.matrix.get(fieldName).entrySet().stream()
        .mapToDouble(Entry::getValue)
        .max().getAsDouble();
  }

  private double getMatrixColumnMinValue(String fieldName) {
    return this.matrix.get(fieldName).entrySet().stream()
        .mapToDouble(Entry::getValue)
        .min().getAsDouble();
  }
}
