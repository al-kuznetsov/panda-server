package com.aol.alkuznetsov.panda.server.converter;

import com.aol.alkuznetsov.panda.server.enums.AppetiteLevel;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AppetiteLevelConverter implements AttributeConverter<AppetiteLevel, Double> {

  @Override
  public Double convertToDatabaseColumn(AppetiteLevel attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getLevel();
  }

  @Override
  public AppetiteLevel convertToEntityAttribute(Double dbData) {

    if (dbData == null) {
      return null;
    }
    return Stream.of(AppetiteLevel.values())
        .filter(value -> value.getLevel() == dbData.doubleValue())
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
