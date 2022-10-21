package com.aol.alkuznetsov.panda.server.service;

import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import com.aol.alkuznetsov.panda.server.model.AnimalType;
import com.aol.alkuznetsov.panda.server.repository.AnimalStatusRepository;
import com.aol.alkuznetsov.panda.server.repository.AnimalTypeRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersistentDataProviderService {
  private final AnimalStatusRepository animalStatusRepository;
  private final AnimalTypeRepository animalTypeRepository;

  @Transactional(readOnly = true)
  public List<Animal> newListOfAnimals() {
    List<Animal> animals = new ArrayList<>(3);
    AnimalType type;
    AnimalStatus status;
    type = animalTypeRepository.findByCode("DOG").orElse(null);
    status = animalStatusRepository.findByCode("DEAD").orElse(null);
    animals.add(
        Animal.builder()
            .name("Панда")
            .birthDate(LocalDate.of(2018, 3, 1))
            .description("Хорошая девочка")
            .fullBio(
                "Собачка среднего размера (20 кг), черно-белого окраса, с коровьими глазами."
                    + "Панда найдена в 24-м микрорайоне, одиночка, дружелюбная и любопытная."
                    + "Большие черные пятна на глазах - отсюда и имя Панда.")
            .imageUrl(null)
            .active(false)
            .type(type)
            .status(status)
            .build());

    return animals;
  }
}
