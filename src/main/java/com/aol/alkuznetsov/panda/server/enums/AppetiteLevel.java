package com.aol.alkuznetsov.panda.server.enums;

import com.aol.alkuznetsov.panda.server.constant.Numbers;
import com.aol.alkuznetsov.panda.server.util.CreationUtils;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppetiteLevel {
  
  NO(Numbers.ONE),
  POOR(Numbers.ONE_HALF),
  GOOD(Numbers.ZERO);
  
  private BigDecimal level;
  
  public static AppetiteLevel getRandomAppetiteLevel() {
    return CreationUtils.getRandomElement(AppetiteLevel.values());
  }
}
