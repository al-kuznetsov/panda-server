package com.aol.alkuznetsov.panda.server.service;

import static com.aol.alkuznetsov.panda.server.constant.GisConst.LOCAL_SRID;

import com.aol.alkuznetsov.panda.server.constant.ResourceConst;
import com.aol.alkuznetsov.panda.server.exception.DataNotFoundException;
import com.aol.alkuznetsov.panda.server.model.AddressType;
import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.AnimalIndicators;
import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import com.aol.alkuznetsov.panda.server.model.AnimalType;
import com.aol.alkuznetsov.panda.server.model.Breed;
import com.aol.alkuznetsov.panda.server.model.Country;
import com.aol.alkuznetsov.panda.server.model.Locality;
import com.aol.alkuznetsov.panda.server.model.LocalityType;
import com.aol.alkuznetsov.panda.server.model.Region;
import com.aol.alkuznetsov.panda.server.model.RegionType;
import com.aol.alkuznetsov.panda.server.model.Spot;
import com.aol.alkuznetsov.panda.server.model.User;
import com.aol.alkuznetsov.panda.server.repository.AddressTypeRepository;
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
import com.aol.alkuznetsov.panda.server.repository.UserRepository;
import com.aol.alkuznetsov.panda.server.util.DebugUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
  private final UserRepository userRepository;

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
    AnimalType catType = animalTypeRepository.findByCode("CAT").orElse(null);
    AnimalStatus deadStatus = animalStatusRepository.findByCode("DEAD").orElse(null);
    AnimalStatus strayStatus = animalStatusRepository.findByCode("STRAY").orElse(null);

    AnimalIndicators indicators1 =
        AnimalIndicators.builder().stress(1).sickness(1).trauma(1).mobility(1).tameness(5).build();
    animals.add(
        Animal.builder()
            .name("Черный")
            .birthDate(LocalDate.of(2018, 1, 1))
            .description("Крупный мохнатый крепкий пес, спокойного характера")
            .fullBio(
                "Крупный, очень спокойный пес, "
                    + "живет у овощных ларьков рядом с магазином \"Олива\"")
            .imageUrl("assets/images/animals/Black.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(indicators1)
            .build());

    AnimalIndicators indicators2 =
        AnimalIndicators.builder().stress(2).sickness(3).trauma(2).mobility(1).tameness(5).build();
    animals.add(
        Animal.builder()
            .name("Черный")
            .birthDate(LocalDate.of(2019, 1, 1))
            .description("Крупный длинноногий пес, активный, много передвигается, дружелюбный")
            .fullBio("Пес появляется преимущественно в 24-м микрорайоне")
            .imageUrl("assets/images/animals/big-guy.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(indicators2)
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
            .imageUrl("assets/images/animals/Panda.jpg")
            .active(false)
            .type(dogType)
            .status(deadStatus)
            .build());

    animals.add(
        Animal.builder()
            .name("Гэри")
            .birthDate(LocalDate.of(2012, 1, 1))
            .description("Мелкий песик, пугливый, принимает еду, на ушах много клещей")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Gary.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(2)
                    .sickness(5)
                    .trauma(2)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Малыш")
            .birthDate(LocalDate.of(2022, 10, 1))
            .description("Щенок, живет у дороги. Это опасно. Остался один.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Baby.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Нана")
            .birthDate(null)
            .description(
                "Приземистая собака светлой окраски, шерсть длинная. Примерная высота 45 см.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl(ResourceConst.DEFAULT_ANIMAL_PLACEHOLDER_IMG_URL)
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Граф")
            .birthDate(LocalDate.of(2019, 10, 1))
            .description("Послушный молодой пес среднего размера.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Graf.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(3)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Тупик")
            .birthDate(null)
            .description("Игривый, подвижный пес-глупыш. Пробует приставать ко всем подряд.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl(ResourceConst.DEFAULT_ANIMAL_PLACEHOLDER_IMG_URL)
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(3)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Герда")
            .birthDate(null)
            .description("НУЖНА ПОМОЩЬ. Собака истощена, кожа да кости. Мало двигается.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Gerda.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(5)
                    .sickness(5)
                    .trauma(1)
                    .mobility(3)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Расти")
            .birthDate(null)
            .description(
                "Рыжая пушистая собака. Беременная. Ходит в стае их трех других бездомных собак")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl(ResourceConst.DEFAULT_ANIMAL_PLACEHOLDER_IMG_URL)
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(3)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Ушастик")
            .birthDate(null)
            .description(
                "НУЖНА ПОМОЩЬ. Собака не опирается на лапу, повреждена передняя левая лапа."
                    + "На ухе желтая бирка, стерилизована и привита.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Ushastik.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(2)
                    .sickness(1)
                    .trauma(4)
                    .mobility(2)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Шарик")
            .birthDate(null)
            .description(
                "НУЖНА ПЕРЕДЕРЖКА. Маленький щенок, прибился к местному магазину Магнит."
                    + "Он один сейчас, нужна обработка и передержка для поиска хозяев")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Sharik.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Жуля")
            .birthDate(null)
            .description(
                "ПОИСК ХОЗЯЕВ. Аккуратная собачка среднего размера. Пожойдет как вариант в квартиру")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Zhulya.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Гавик")
            .birthDate(null)
            .description(
                "НУЖНА ПОМОЩЬ. Звонкий песик, похоже, что приболел. Нужне визит к ветеринару.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl(ResourceConst.DEFAULT_ANIMAL_PLACEHOLDER_IMG_URL)
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(4)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Глаша")
            .birthDate(null)
            .description(
                "НУЖНА ПОМОЩЬ. Нужно вылечить и пристроить кошку. Холмает на задные лапы."
                    + "Возможно, выпала и окна")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Glasha-cat.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Томми")
            .birthDate(null)
            .description("Томми красивый черрый кот, живет на трубах у рынка.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl(ResourceConst.DEFAULT_ANIMAL_PLACEHOLDER_IMG_URL)
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Чумазик")
            .birthDate(null)
            .description("НУЖНА ПОМОЩЬ. Кошечка болеет, грязная, неухоженная. Нужно лечение.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Chumazik.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Чумазик")
            .birthDate(null)
            .description("НУЖНА ПОМОЩЬ. Кошечка болеет, грязная, неухоженная. Нужно лечение.")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Chumazik.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

      animals.add(
        Animal.builder()
            .name("Кузьмич")
            .birthDate(LocalDate.of(2017, 10, 18))
            .description("Брошенный на даче кот Кузьмич ищет добрых хозяев")
            .fullBio(
                "Брошенный на даче кот Кузьмич ищет добрых хозяев. Кот в крапушку с красивым белым воротничком, очень ласковый. Кота бросили на дачах в районе Берды. Временно котика пристроили у себя, но нет возможности оставить")
            .imageUrl("assets/images/animals/kuzmich.png")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(indicators1)
            .build());

    AnimalIndicators indicators3 =
        AnimalIndicators.builder().stress(2).sickness(3).trauma(2).mobility(1).tameness(5).build();
    animals.add(
        Animal.builder()
            .name("Вафля")
            .birthDate(LocalDate.of(2022, 1, 1))
            .description("ПОСТ ПО ПРОСЬБЕ.Найден щенок подросток , задние лапы волочит,наверное сбила машина")
            .fullBio("Карачи! ТК Муравейник!Помогите с транспортировкой и лечением щенка. Звонить по телефону +799999999999")
            .imageUrl("assets/images/animals/vaflya.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(indicators2)
            .build());

    animals.add(
        Animal.builder()
            .name("Шая")
            .birthDate(LocalDate.of(2018, 3, 1))
            .description("Нужна помощь! Черная собака со сломанной лапой")
            .fullBio(
                "На Дзержинского..., лежит собака. Сломана лапа. ")
            .imageUrl("assets/images/animals/Panda.jpg")
            .active(true)
            .type(dogType)
             .indicators(
                AnimalIndicators.builder()
                    .stress(2)
                    .sickness(5)
                    .trauma(2)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Изюминка")
            .birthDate(LocalDate.of(2021, 1, 1))
            .description("Ласковая кошечка ищет дом.")
            .fullBio("Девочка 1, 2 года ищет дом. К лотку приучена.")
            .imageUrl("assets/images/animals/Gary.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(2)
                    .sickness(5)
                    .trauma(2)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Котята")
            .birthDate(LocalDate.of(2022, 11, 1))
            .description("Срочно нужна кормящая кошка! Котят выкинули")
            .fullBio("Вчера уже ночью у нас рядом с домой (Транспортная ) в мусорный бак (нового типа) были выброшены в пакете 4 совсем маленьких котёнка (слепые ещё). Соседи достали из бака. Сейчас находятся у соседки. Номер для связи +79277777777 Катя. КОТЯТА БЕЗ КОШКИ. НУЖНА КОШКА ДЛЯ ВЫКОРМКИ И ВЫЖИВАНИЯ КОТЯТ. ПРОСИМ ОТКЛИКНУТЬСЯ")
            .imageUrl("assets/images/animals/kittens.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Злодик")
            .birthDate(null)
            .description(
                "Пес специфической внешности ищет хозяев. Вероятнее всего полупородистый")
            .fullBio("Найден полупородистый пес в районе 18 мкр-на. Старые или новые хозяева отзовитесь. Пес вполне себе воспитанный")
            .imageUrl("assets/images/animals/zlodik.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Тухтамыш")
            .birthDate(LocalDate.of(2019, 10, 1))
            .description("Пес небольших размеров. Пугливый. Голодный. ")
            .fullBio("Песик обитает в районе Комсомольской.")
            .imageUrl("assets/images/animals/tukhtamish.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(3)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Наоми")
            .birthDate(null)
            .description("Сиамская кошечка красивая,ручная, чистая, упитанная")
            .fullBio("Около заброшенных вагончиков живёт сиамская кошечка, появилась недавно, ручная, чистая, упитанная. Может кто-то захочет приютить")
            .imageUrl("assets/images/animals/naomi.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(3)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Вася")
            .birthDate(null)
            .description("ПОСТ ПО ПРОСЬБЕ!!!Котику на Салмышской нужна помощь!")
            .fullBio("Кот в плохом состоянии. Вероятно он болен! Другие коты его обижают! Помогите котику!")
            .imageUrl("assets/images/animals/vasya.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(5)
                    .sickness(5)
                    .trauma(1)
                    .mobility(3)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Тобик")
            .birthDate(null)
            .description(
                "Черный, коротколапый, дружелюбный песик нуждается в помощи")
            .fullBio("Заметили данного пса в районе Тепличной улицы п.Кушкуль. Пес очень дружелюбный, но к сожалению все уши увешаны клещами. Пес нуждается в лечении и обработке от паразитов. Неравнодушных просьба помочь.")
            .imageUrl(ResourceConst.DEFAULT_ANIMAL_PLACEHOLDER_IMG_URL)
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(3)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Грызлик")
            .birthDate(null)
            .description(
                "Найден мальчик такса в ошейнике. Напуган, дрожит"
                    + "На ухе желтая бирка, стерилизована и привита.")
            .fullBio("Срочно ребята !!! Люди нашли таксу мальчик улица Чкалова в ошейнике завели в подъезд "
                +"От еды и воды отказывается сильно дрожит очень замерший и простывший."
                +"Вдруг кто видел объявление о пропаже позвоните мне 89877777777777")
            .imageUrl("assets/images/animals/gryzlik.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(2)
                    .sickness(1)
                    .trauma(4)
                    .mobility(2)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Шарик")
            .birthDate(null)
            .description(
                "НУЖНА ПЕРЕДЕРЖКА. Маленький щенок, прибился к местному магазину Магнит."
                    + "Он один сейчас, нужна обработка и передержка для поиска хозяев")
            .fullBio(ResourceConst.EMPTY_STRING_PLACEHOLDER)
            .imageUrl("assets/images/animals/Sharik.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Мухтар")
            .birthDate(null)
            .description(
                "Замечена красивая немецкая овчарка мальчик в районе Терешковой")
            .fullBio("Найдена немецкая овчарка. По возрасту выглядит как подросток."
            +" Мальчик очень дружелюбный и активный."
                +"Песик в ошейнике. Вероятнее убежал, и его ищут хозяева.")
            .imageUrl("assets/images/animals/mukhtar.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Лютик")
            .birthDate(null)
            .description(
                "НУЖНА ПОМОЩЬ. Щенок-сирота на обочине дороги.")
            .fullBio("Остановка 19 микрорайон, на дороге мать сбила машина, щенок сидит возле дороги, не уходит.")
            .imageUrl("assets/images/animals/lutik.jpg")
            .active(true)
            .type(dogType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(4)
                    .trauma(1)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Трикси")
            .birthDate(null)
            .description(
                "НУЖНА ПОМОЩЬ.Нужна передержка для кошки, до апреля месяца.")
            .fullBio("Она уличная, людей боится."
                +"Но если оставить на улице, то погибнет от морозов.")
            .imageUrl("assets/images/animals/triksi.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Анфиса")
            .birthDate(null)
            .description("Анфиса - беременная кошка,найденная на улице."
                +"Она вот-вот должна родить котят, я боюсь, что они все умрут от холода.")
            .fullBio(" Кошка очень добрая, ласковая, сама просит помощи будто. Скажите, вы могли бы помочь?"
                +"Я готова предоставить с ней миски, лоток, плед , корм и все что ей нужно для ее проживания, помогать финансово."
                + "Очень за неё переживаю, за судьбу котят. К сожалению, я не смогу оставить кошку у себя надолго - я совсем скоро уеду и я себе разорву все сердце, когда придёт время снова ее и котят оставлять на улице")
            .imageUrl("assets/images/animals/anfisa.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Зорро")
            .birthDate(null)
            .description("НУЖНА ПОМОЩЬ.  Вот такого черного кота Зорро выкинули в подъезд. ")
            .fullBio("Мальчик ласковый и добрый, но люди против, чтобы пушистый жил в подъезде."
                + "Может быть найдется человек, готовый приютить красавца?")
            .imageUrl("assets/images/animals/zorro.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
            .build());

    animals.add(
        Animal.builder()
            .name("Бродский")
            .birthDate(null)
            .description("В районе Пятерочки в Кушкулях замечен одноглазый жалкого вида кот.")
            .fullBio(" У супермаркета Пятерочки  ходит жалкого вида худой одноглазый серый кот. Голодный, ел жадно с мурлыканьем."
            + "Кажется, полуслепой. Хоть и ориентируется, но странно ел, как будто еду не мог найти сразу у себя под носом."
            +"Это, собственно и есть причина этого поста, если ослепнет, долго котофей не протянет."
            +" Если кто может взять на попечение, было бы супер. В случае лечения, помогу с финансами.")
            .imageUrl("assets/images/animals/brodskiy.jpg")
            .active(true)
            .type(catType)
            .status(strayStatus)
            .indicators(
                AnimalIndicators.builder()
                    .stress(1)
                    .sickness(1)
                    .trauma(3)
                    .mobility(1)
                    .tameness(5)
                    .build())
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

  @Transactional
  public List<User> newListOfUsers(boolean persist) {

    List<User> users = new ArrayList<>();

    users.add(
        User.builder()
            .name("admin")
            .email("admin@panda.com")
            .jobTitle("admin")
            .phone(ResourceConst.DEFAULT_USER_PHONE_NUMBER)
            .imageUrl(ResourceConst.DEFAULT_USER_AVATAR_URL)
            .userCode(UUID.randomUUID().toString())
            .build());

    users.add(
        User.builder()
            .name("Мария")
            .email("mary@panda.com")
            .jobTitle("user")
            .phone(ResourceConst.DEFAULT_USER_PHONE_NUMBER)
            .imageUrl(ResourceConst.DEFAULT_USER_AVATAR_URL)
            .userCode(UUID.randomUUID().toString())
            .build());

    users.add(
        User.builder()
            .name("Женя")
            .email("evgeniya@panda.com")
            .jobTitle("user")
            .phone(ResourceConst.DEFAULT_USER_PHONE_NUMBER)
            .imageUrl(ResourceConst.DEFAULT_USER_AVATAR_URL)
            .userCode(UUID.randomUUID().toString())
            .build());

    if (persist) {
      List<User> savedUsers = userRepository.saveAll(users);
      // TODO: add debug logging
      return savedUsers;
    } else {
      return users;
    }
  }
}
