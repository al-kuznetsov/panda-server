package com.aol.alkuznetsov.panda.server.converter;

import com.aol.alkuznetsov.panda.server.enums.ConsciousnessLevel;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConsciousnessLevelConverter implements AttributeConverter<ConsciousnessLevel, Double> {

  @Override
  public Double convertToDatabaseColumn(ConsciousnessLevel attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getLevel();
  }

  @Override
  public ConsciousnessLevel convertToEntityAttribute(Double dbData) {

    if (dbData == null) {
      return null;
    }
    return Stream.of(ConsciousnessLevel.values())
        .filter(value -> value.getLevel() == dbData.doubleValue())
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
