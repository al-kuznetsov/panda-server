package com.aol.alkuznetsov.panda.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MobilityLossLevel {
  
  COMPLETELY_LOST(1.0),
  PARTIALLY_LOST(0.5),
  NO(0.0);
  
  private double level;

}
