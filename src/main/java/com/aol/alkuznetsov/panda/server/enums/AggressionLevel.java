package com.aol.alkuznetsov.panda.server.enums;

import com.aol.alkuznetsov.panda.server.constant.Numbers;
import com.aol.alkuznetsov.panda.server.util.CreationUtils;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AggressionLevel {

  NO(Numbers.ONE),
  AVERAGE(Numbers.ONE_HALF),
  AGGRESSIVE(Numbers.ZERO);

  private BigDecimal level;
  
  public static AggressionLevel getRandomAggressionLevel() {
    return CreationUtils.getRandomElement(AggressionLevel.values());
  }
}
