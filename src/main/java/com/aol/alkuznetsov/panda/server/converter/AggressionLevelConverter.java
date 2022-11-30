package com.aol.alkuznetsov.panda.server.converter;

import com.aol.alkuznetsov.panda.server.enums.AggressionLevel;
import java.math.BigDecimal;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AggressionLevelConverter implements AttributeConverter<AggressionLevel, BigDecimal> {

  @Override
  public BigDecimal convertToDatabaseColumn(AggressionLevel attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getLevel();
  }

  @Override
  public AggressionLevel convertToEntityAttribute(BigDecimal dbData) {

    if (dbData == null) {
      return null;
    }
    return Stream.of(AggressionLevel.values())
        .filter(value -> value.getLevel().equals(dbData))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
