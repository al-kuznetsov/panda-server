package com.aol.alkuznetsov.panda.server.util;

import com.aol.alkuznetsov.panda.server.model.AddressType;
import com.aol.alkuznetsov.panda.server.model.Animal;
import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import com.aol.alkuznetsov.panda.server.model.AnimalType;
import com.aol.alkuznetsov.panda.server.model.Breed;
import com.aol.alkuznetsov.panda.server.model.Country;
import com.aol.alkuznetsov.panda.server.model.Locality;
import com.aol.alkuznetsov.panda.server.model.LocalityType;
import com.aol.alkuznetsov.panda.server.model.Region;
import com.aol.alkuznetsov.panda.server.model.RegionType;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DebugUtils {
  public static String getMessageWithAnimalsList(String message, List<Animal> animals) {
    List<ImmutableTriple<Long, String, String>> triples =
        animals.stream()
            .map(
                item ->
                    new ImmutableTriple<>(item.getId(), item.getName(), item.getType().getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithCountryList(String message, List<Country> countries) {
    List<ImmutableTriple<Long, String, String>> triples =
        countries.stream()
            .map(
                item ->
                    new ImmutableTriple<>(
                        item.getId(), item.getAlphaTwoCode(), item.getShortName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithRegionTypeList(String message, List<RegionType> regionTypes) {
    List<ImmutableTriple<Long, String, String>> triples =
        regionTypes.stream()
            .map(item -> new ImmutableTriple<>(item.getId(), item.getCode(), item.getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithLocalityTypeList(
      String message, List<LocalityType> localityTypes) {
    List<ImmutableTriple<Long, String, String>> triples =
        localityTypes.stream()
            .map(item -> new ImmutableTriple<>(item.getId(), item.getCode(), item.getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithAddressTypeList(
      String message, List<AddressType> addressTypes) {
    List<ImmutableTriple<Long, String, String>> triples =
        addressTypes.stream()
            .map(item -> new ImmutableTriple<>(item.getId(), item.getCode(), item.getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithAnimalTypeList(String message, List<AnimalType> addressTypes) {
    List<ImmutableTriple<Long, String, String>> triples =
        addressTypes.stream()
            .map(item -> new ImmutableTriple<>(item.getId(), item.getCode(), item.getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithBreedList(String message, List<Breed> addressTypes) {
    List<ImmutableTriple<Long, String, String>> triples =
        addressTypes.stream()
            .map(
                item ->
                    new ImmutableTriple<>(item.getId(), item.getName(), item.getType().getCode()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithAnimalStatusList(
      String message, List<AnimalStatus> addressTypes) {
    List<ImmutableTriple<Long, String, String>> triples =
        addressTypes.stream()
            .map(item -> new ImmutableTriple<>(item.getId(), item.getCode(), item.getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithRegionList(String message, List<Region> regions) {
    List<ImmutableTriple<Long, String, String>> triples =
        regions.stream()
            .map(
                item ->
                    new ImmutableTriple<>(item.getId(), item.getName(), item.getType().getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }

  public static String getMessageWithLocalityList(String message, List<Locality> localities) {
    List<ImmutableTriple<Long, String, String>> triples =
        localities.stream()
            .map(
                item ->
                    new ImmutableTriple<>(item.getId(), item.getName(), item.getType().getName()))
            .collect(Collectors.toList());
    String resultString = StringUtils.join(triples, System.lineSeparator());
    return String.format("%s%s%s", message, System.lineSeparator(), resultString);
  }
}
