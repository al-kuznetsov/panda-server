package com.aol.alkuznetsov.panda.server.enums;

import com.aol.alkuznetsov.panda.server.constant.Numbers;
import com.aol.alkuznetsov.panda.server.util.CreationUtils;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConsciousnessLevel {
  
  UNCONSCIOUS(Numbers.ONE),
  DEPRESSED(Numbers.ONE_HALF),
  CONSCIOUS(Numbers.ZERO);
  
  private BigDecimal level;
  
  public static ConsciousnessLevel getRandomConsiousnessLevel() {
    return CreationUtils.getRandomElement(ConsciousnessLevel.values());
  }
}
