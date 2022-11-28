package com.aol.alkuznetsov.panda.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConsciousnessLevel {
  
  UNCONSCIOUS(1.0),
  DEPRESSED(0.5),
  CONSCIOUS(0.0);
  
  private double level;
}
