package com.aol.alkuznetsov.panda.server.enums;

import com.aol.alkuznetsov.panda.server.constant.Numbers;
import com.aol.alkuznetsov.panda.server.util.CreationUtils;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MobilityLossLevel {
  
  COMPLETELY_LOST(Numbers.ONE),
  PARTIALLY_LOST(Numbers.ONE_HALF),
  NO(Numbers.ZERO);
  
  private BigDecimal level;
  
  public static MobilityLossLevel getRandomMobilityLossLevel() {
    return CreationUtils.getRandomElement(MobilityLossLevel.values());
  }
}
