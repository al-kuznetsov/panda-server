package com.aol.alkuznetsov.panda.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BleedingLevel {
  
  HEAVY(1.0),
  LIGHT(0.5),
  NO(0.0);
  
  private double level;
}
