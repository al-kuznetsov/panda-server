package com.aol.alkuznetsov.panda.server.converter;

import com.aol.alkuznetsov.panda.server.enums.AggressionLevel;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AggressionLevelConverter implements AttributeConverter<AggressionLevel, Double> {

  @Override
  public Double convertToDatabaseColumn(AggressionLevel attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getLevel();
  }

  @Override
  public AggressionLevel convertToEntityAttribute(Double dbData) {

    if (dbData == null) {
      return null;
    }
    return Stream.of(AggressionLevel.values())
        .filter(value -> value.getLevel() == dbData.doubleValue())
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
