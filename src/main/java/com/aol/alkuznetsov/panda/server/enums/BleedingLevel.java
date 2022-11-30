package com.aol.alkuznetsov.panda.server.enums;

import com.aol.alkuznetsov.panda.server.constant.Numbers;
import com.aol.alkuznetsov.panda.server.util.CreationUtils;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BleedingLevel {
  
  HEAVY(Numbers.ONE),
  LIGHT(Numbers.ONE_HALF),
  NO(Numbers.ZERO);
  
  private BigDecimal level;
  
  public static BleedingLevel getRandomBleedingLevel() {
    return CreationUtils.getRandomElement(BleedingLevel.values());
  }
}
