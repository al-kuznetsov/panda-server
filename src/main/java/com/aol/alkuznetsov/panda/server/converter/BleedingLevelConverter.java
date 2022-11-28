package com.aol.alkuznetsov.panda.server.converter;

import com.aol.alkuznetsov.panda.server.enums.BleedingLevel;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BleedingLevelConverter implements AttributeConverter<BleedingLevel, Double> {

  @Override
  public Double convertToDatabaseColumn(BleedingLevel attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getLevel();
  }

  @Override
  public BleedingLevel convertToEntityAttribute(Double dbData) {

    if (dbData == null) {
      return null;
    }
    return Stream.of(BleedingLevel.values())
        .filter(value -> value.getLevel() == dbData.doubleValue())
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
