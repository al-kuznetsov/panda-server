package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDto {
  private Long id;
  private String alphaTwoCode;
  private String shortName;
  private String name;
}
