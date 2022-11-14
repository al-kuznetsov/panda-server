package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.model.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Настоящий класс выполняет расчет криетриев для ранжирования набора животных по заданному набору
 * критериев для каждого животного методом векторной оптимизации с применением аддитивной свертки.
 */
@Service
public class CriteriaCalculationService {

  private static final int MAX_NUMBER_OF_ANIMALS = 5;

  List<Pair<Animal, Double>> calculateCriteria(List<Animal> animals) {
    Assert.notEmpty(animals, "Animal list must not be empty");
    Assert.isTrue(
        animals.size() <= MAX_NUMBER_OF_ANIMALS, "Animal list must contain no more than 5 items");
    int size = animals.size();

    // TODO add the calculation
    // 1. Build the matrix

    // 2. Normalization

    // 3. Do the additive convolution

    // 4. Return the result criteria vector
    List<Pair<Animal, Double>> criteriaVector = new ArrayList<>(size);
    animals.forEach(
        animal -> {
          Pair<Animal, Double> criteriaPair =
              Pair.of(animal, ThreadLocalRandom.current().nextDouble(9.0));
          criteriaVector.add(criteriaPair);
        });

    return criteriaVector;
  }
}
