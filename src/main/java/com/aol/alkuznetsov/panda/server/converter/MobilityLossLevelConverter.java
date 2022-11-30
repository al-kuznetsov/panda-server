package com.aol.alkuznetsov.panda.server.converter;

import com.aol.alkuznetsov.panda.server.enums.MobilityLossLevel;
import java.math.BigDecimal;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MobilityLossLevelConverter implements AttributeConverter<MobilityLossLevel, BigDecimal> {

  @Override
  public BigDecimal convertToDatabaseColumn(MobilityLossLevel attribute) {

    if (attribute == null) {
      return null;
    }
    return attribute.getLevel();
  }

  @Override
  public MobilityLossLevel convertToEntityAttribute(BigDecimal dbData) {

    if (dbData == null) {
      return null;
    }
    return Stream.of(MobilityLossLevel.values())
        .filter(value -> value.getLevel().equals(dbData))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

}
