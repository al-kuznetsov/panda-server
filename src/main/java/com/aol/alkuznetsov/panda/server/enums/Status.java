package com.aol.alkuznetsov.panda.server.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
  STRAY("STRAY"),
  SHELTERED("SHELTERED"),
  HAS_GUARDIAN("HAS_GUARDIAN"),
  HAS_OWNER("HAS_OWNER"),
  MISSING("MISSING"),
  DEAD("DEAD");

  private final String statusCode;
}
