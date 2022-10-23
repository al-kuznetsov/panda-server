package com.aol.alkuznetsov.panda.server.service;

import static com.aol.alkuznetsov.panda.server.constant.GisConst.LOCAL_SRID;

import com.aol.alkuznetsov.panda.server.exception.DataNotFoundException;
import com.aol.alkuznetsov.panda.server.model.AddressType;
import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.AnimalCriteria;
import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import com.aol.alkuznetsov.panda.server.model.AnimalType;
import com.aol.alkuznetsov.panda.server.model.Breed;
import com.aol.alkuznetsov.panda.server.model.Country;
import com.aol.alkuznetsov.panda.server.model.Locality;
import com.aol.alkuznetsov.panda.server.model.LocalityType;
import com.aol.alkuznetsov.panda.server.model.Region;
import com.aol.alkuznetsov.panda.server.model.RegionType;
import com.aol.alkuznetsov.panda.server.model.Spot;
import com.aol.alkuznetsov.panda.server.repository.AddressTypeRepository;
import com.aol.alkuznetsov.panda.server.repository.AnimalCriteriaRepository;
import com.aol.alkuznetsov.panda.server.repository.AnimalRepository;
import com.aol.alkuznetsov.panda.server.repository.AnimalStatusRepository;
import com.aol.alkuznetsov.panda.server.repository.AnimalTypeRepository;
import com.aol.alkuznetsov.panda.server.repository.BreedRepository;
import com.aol.alkuznetsov.panda.server.repository.CountryRepository;
import com.aol.alkuznetsov.panda.server.repository.LocalityRepository;
import com.aol.alkuznetsov.panda.server.repository.LocalityTypeRepository;
import com.aol.alkuznetsov.panda.server.repository.RegionRepository;
import com.aol.alkuznetsov.panda.server.repository.RegionTypeRepository;
import com.aol.alkuznetsov.panda.server.repository.SpotRepository;
import com.aol.alkuznetsov.panda.server.util.DebugUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PersistentDataProviderService {
  private final AnimalStatusRepository animalStatusRepository;
  private final AnimalTypeRepository animalTypeRepository;
  private final RegionTypeRepository regionTypeRepository;
  private final LocalityTypeRepository localityTypeRepository;
  private final CountryRepository countryRepository;
  private final RegionRepository regionRepository;
  private final LocalityRepository localityRepository;
  private final AddressTypeRepository addressTypeRepository;
  private final BreedRepository breedRepository;
  private final AnimalRepository animalRepository;
  private final SpotRepository spotRepository;
  private final AnimalCriteriaRepository animalCriteriaRepository;

  @Transactional
  public List<Country> newListOfCountries(boolean persist) {
    List<Country> countries = new ArrayList<>(4);
    countries.add(
        Country.builder()
            .alphaTwoCode("US")
            .shortName("США")
            .name("Соединенные Штаты Америки")
            .build());
    countries.add(
        Country.builder()
            .alphaTwoCode("RU")
            .shortName("Россия")
            .name("Российская Федерация")
            .build());
    countries.add(
        Country.builder()
            .alphaTwoCode("KZ")
            .shortName("Казахстан")
            .name("Республика Казахстан")
            .build());
    countries.add(
        Country.builder().alphaTwoCode("IN").shortName("Индия").name("Республика Индия").build());
    if (persist) {
      List<Country> savedCountries = countryRepository.saveAll(countries);
      log.debug(
          DebugUtils.getMessageWithCountryList("Saved countries to Database", savedCountries));
      return savedCountries;
    } else {
      return countries;
    }
  }

  @Transactional
  public List<RegionType> newListOfRegionTypes(boolean persist) {
    List<RegionType> regionTypes = new ArrayList<>(3);
    regionTypes.add(RegionType.builder().code("OBLAST").name("область").build());
    regionTypes.add(RegionType.builder().code("RESPUBLIKA").name("республика").build());
    regionTypes.add(RegionType.builder().code("GOROD").name("город").build());
    if (persist) {
      List<RegionType> savedRegionTypes = regionTypeRepository.saveAll(regionTypes);
      log.debug(
          DebugUtils.getMessageWithRegionTypeList(
              "Saved region types to Database", savedRegionTypes));
      return savedRegionTypes;
    } else {
      return regionTypes;
    }
  }

  @Transactional
  public List<Region> newListOfRegions(boolean persist) {
    List<Region> regions = new ArrayList<>(4);
    RegionType republicType =
        regionTypeRepository.findByCode("RESPUBLIKA").orElseThrow(DataNotFoundException::new);
    RegionType oblastType =
        regionTypeRepository.findByCode("OBLAST").orElseThrow(DataNotFoundException::new);
    RegionType gorodType =
        regionTypeRepository.findByCode("GOROD").orElseThrow(DataNotFoundException::new);

    regions.add(Region.builder().type(republicType).name("Башкортостан").build());
    regions.add(Region.builder().type(republicType).name("Татарстан").build());
    regions.add(Region.builder().type(oblastType).name("Оребургская").build());
    regions.add(Region.builder().type(gorodType).name("Москва").build());

    if (persist) {
      List<Region> savedRegions = regionRepository.saveAll(regions);
      log.debug(DebugUtils.getMessageWithRegionList("Saved regions to Database", savedRegions));
      return savedRegions;
    } else {
      return regions;
    }
  }

  @Transactional
  public List<LocalityType> newListOfLocalityTypes(boolean persist) {
    List<LocalityType> localityTypes = new ArrayList<>(3);
    localityTypes.add(LocalityType.builder().code("GOROD").name("город").build());
    localityTypes.add(LocalityType.builder().code("DEREVNYA").name("деревня").build());
    localityTypes.add(LocalityType.builder().code("POSELOK").name("поселок").build());
    if (persist) {
      List<LocalityType> savedLocalityTypes = localityTypeRepository.saveAll(localityTypes);
      log.debug(
          DebugUtils.getMessageWithLocalityTypeList(
              "Saved locality types to Databse", savedLocalityTypes));
      return savedLocalityTypes;
    } else {
      return localityTypes;
    }
  }

  @Transactional
  public List<Locality> newListOfLocalities(boolean persist) {
    List<Locality> localities = new ArrayList<>(4);
    LocalityType gorodType =
        localityTypeRepository.findByCode("GOROD").orElseThrow(DataNotFoundException::new);

    localities.add(Locality.builder().type(gorodType).name("Оренбург").build());
    localities.add(Locality.builder().type(gorodType).name("Москва").build());
    localities.add(Locality.builder().type(gorodType).name("Уфа").build());
    localities.add(Locality.builder().type(gorodType).name("Казань").build());

    if (persist) {
      List<Locality> savedLocalities = localityRepository.saveAll(localities);
      log.debug(
          DebugUtils.getMessageWithLocalityList("Saved localities to Database", savedLocalities));
      return savedLocalities;
    } else {
      return localities;
    }
  }

  @Transactional
  public List<AddressType> newListOfAddressType(boolean persist) {
    List<AddressType> addressTypes = new ArrayList<>(4);
    addressTypes.add(AddressType.builder().code("POSTAL").name("Почтовый адрес").build());
    addressTypes.add(
        AddressType.builder().code("BILLING").name("Адрес для выставления счетов").build());
    addressTypes.add(AddressType.builder().code("DELIVERY").name("Адрес доставки").build());
    addressTypes.add(AddressType.builder().code("MAIN").name("Основной адрес").build());

    if (persist) {
      List<AddressType> savedAddressTypes = addressTypeRepository.saveAll(addressTypes);
      log.debug(
          DebugUtils.getMessageWithAddressTypeList(
              "Saved address types to Database", savedAddressTypes));
      return savedAddressTypes;
    } else {
      return addressTypes;
    }
  }

  @Transactional
  public List<AnimalType> newListOfAnimalType(boolean persist) {
    List<AnimalType> animalTypes = new ArrayList<>(4);
    animalTypes.add(AnimalType.builder().code("DOG").name("Собака").description("Собака").build());
    animalTypes.add(AnimalType.builder().code("CAT").name("Кошка").description("Кошка").build());

    if (persist) {
      List<AnimalType> savedAnimalTypes = animalTypeRepository.saveAll(animalTypes);
      log.debug(
          DebugUtils.getMessageWithAnimalTypeList(
              "Saved animal types to Database", savedAnimalTypes));
      return savedAnimalTypes;
    } else {
      return animalTypes;
    }
  }

  @Transactional
  public List<Breed> newListOfBreed(boolean persist) {
    List<Breed> breeds = new ArrayList<>(4);
    AnimalType dogType =
        animalTypeRepository.findByCode("DOG").orElseThrow(DataNotFoundException::new);
    AnimalType catType =
        animalTypeRepository.findByCode("CAT").orElseThrow(DataNotFoundException::new);
    breeds.add(Breed.builder().name("Беспородный").type(dogType).build());
    breeds.add(Breed.builder().name("Беспородный").type(catType).build());
    breeds.add(Breed.builder().name("Пудель").type(dogType).build());
    breeds.add(Breed.builder().name("Сфинкс").type(catType).build());

    if (persist) {
      List<Breed> savedBreeds = breedRepository.saveAll(breeds);
      log.debug(DebugUtils.getMessageWithBreedList("Saved breeds to Database", savedBreeds));
      return savedBreeds;
    } else {
      return breeds;
    }
  }

  @Transactional
  public List<AnimalStatus> newListOfAnimalStatus(boolean persist) {
    List<AnimalStatus> addressTypes = new ArrayList<>(4);
    addressTypes.add(AnimalStatus.builder().code("STRAY").name("Бездомный").build());
    addressTypes.add(AnimalStatus.builder().code("SHELTERED").name("В приюте").build());
    addressTypes.add(AnimalStatus.builder().code("HAS_GUARDIAN").name("С куратором").build());
    addressTypes.add(AnimalStatus.builder().code("HAS_OWNER").name("Хозяйский").build());
    addressTypes.add(AnimalStatus.builder().code("MISSING").name("Потерян").build());
    addressTypes.add(AnimalStatus.builder().code("DEAD").name("Мертв").build());

    if (persist) {
      List<AnimalStatus> savedAddressTypes = animalStatusRepository.saveAll(addressTypes);
      log.debug(
          DebugUtils.getMessageWithAnimalStatusList(
              "Saved animal statuses to Database", savedAddressTypes));
      return savedAddressTypes;
    } else {
      return addressTypes;
    }
  }

  @Transactional(readOnly = true)
  public List<Animal> newListOfAnimals(boolean persist) {
    List<Animal> animals = new ArrayList<>(3);

    AnimalType dogType = animalTypeRepository.findByCode("DOG").orElse(null);
    AnimalStatus deadStatus = animalStatusRepository.findByCode("DEAD").orElse(null);
    AnimalStatus strayStatus = animalStatusRepository.findByCode("STRAY").orElse(null);

    AnimalCriteria criteria1 =
        AnimalCriteria.builder().stress(1).sickness(1).trauma(1).mobility(1).tameness(5).build();
    animals.add(
        Animal.builder()
            .name("Черный")
            .birthDate(LocalDate.of(2018, 1, 1))
            .description("Крупный мохнатый крепкий пес, спокойного характера")
            .fullBio(
                "Крупный, очень спокойный пес, "
                    + "живет у овощных ларьков рядом с магазином \"Олива\"")
            .imageUrl(null)
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .criteria(criteria1)
            .build());

    AnimalCriteria criteria2 =
        AnimalCriteria.builder().stress(2).sickness(3).trauma(2).mobility(1).tameness(5).build();
    animals.add(
        Animal.builder()
            .name(null)
            .birthDate(LocalDate.of(2019, 1, 1))
            .description("Крупный длинноногий пес, активный, много передвигается, дружелюбный")
            .fullBio("Пес появляется преимущественно в 24-м микрорайоне")
            .imageUrl(null)
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .criteria(criteria2)
            .build());

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
            .type(dogType)
            .status(deadStatus)
            .build());

    if (persist) {
      List<Animal> savedAnimals = animalRepository.saveAll(animals);
      log.debug(DebugUtils.getMessageWithAnimalsList("Saved animals to Database", savedAnimals));
      return savedAnimals;
    } else {
      return animals;
    }
  }

  @Transactional
  public List<Spot> newListOfSpots(boolean persist) {
    Spot spot1 = new Spot();
    Point<C2D> point1 = DSL.point(LOCAL_SRID, DSL.c(51.771959, 55.194175));
    spot1.setPoint(point1);
    // home
    Spot spot2 = new Spot();
    Point<C2D> point2 = DSL.point(LOCAL_SRID, DSL.c(51.773059, 55.192671));
    spot2.setPoint(point2);
    List<Spot> spots = List.of(spot1, spot2);
    if (persist) {
      List<Spot> savedSpots = spotRepository.saveAll(spots);
      log.debug(
          "Saved spots to Database: {} {}",
          System.lineSeparator(),
          StringUtils.join(savedSpots, System.lineSeparator()));
      return savedSpots;
    } else {
      return spots;
    }
  }
}
