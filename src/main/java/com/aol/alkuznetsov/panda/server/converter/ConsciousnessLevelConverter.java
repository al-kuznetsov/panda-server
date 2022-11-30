package com.aol.alkuznetsov.panda.server.converter;

import com.aol.alkuznetsov.panda.server.enums.ConsciousnessLevel;
import java.math.BigDecimal;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConsciousnessLevelConverter implements AttributeConverter<ConsciousnessLevel, BigDecimal> {

  @Override
  public BigDecimal convertToDatabaseColumn(ConsciousnessLevel attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getLevel();
  }

  @Override
  public ConsciousnessLevel convertToEntityAttribute(BigDecimal dbData) {

    if (dbData == null) {
      return null;
    }
    return Stream.of(ConsciousnessLevel.values())
        .filter(value -> value.getLevel().equals(dbData))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
