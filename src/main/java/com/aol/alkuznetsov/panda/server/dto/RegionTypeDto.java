package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegionTypeDto {
  private Long id;
  private String code;
  private String name;
}
