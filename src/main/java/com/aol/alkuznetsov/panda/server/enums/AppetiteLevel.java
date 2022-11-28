package com.aol.alkuznetsov.panda.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AppetiteLevel {
  
  NO(1.0),
  POOR(0.5),
  GOOD(0.0);
  
  private double level;
}
