package com.aol.alkuznetsov.panda.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AggressionLevel {

  NO(1.0), AVERAGE(0.5), AGGRESSIVE(0);

  private double level;
}
