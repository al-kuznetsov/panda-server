package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegionDto {
  private Long id;
  private String name;
  private RegionTypeDto type;
}
