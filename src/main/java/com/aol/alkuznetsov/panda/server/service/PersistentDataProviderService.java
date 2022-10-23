package com.aol.alkuznetsov.panda.server.service;

import static com.aol.alkuznetsov.panda.server.constant.GisConst.LOCAL_SRID;

import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import com.aol.alkuznetsov.panda.server.model.AnimalType;
import com.aol.alkuznetsov.panda.server.model.Spot;
import com.aol.alkuznetsov.panda.server.repository.AnimalStatusRepository;
import com.aol.alkuznetsov.panda.server.repository.AnimalTypeRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
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

  public List<Spot> newListOfSpots() {
    Spot spot1 = new Spot();
    Point<C2D> point1 = DSL.point(LOCAL_SRID, DSL.c(51.771959, 55.194175));
    spot1.setPoint(point1);
    // home
    Spot spot2 = new Spot();
    Point<C2D> point2 = DSL.point(LOCAL_SRID, DSL.c(51.773059, 55.192671));
    spot2.setPoint(point2);
    return List.of(spot1, spot2);
  }
}
