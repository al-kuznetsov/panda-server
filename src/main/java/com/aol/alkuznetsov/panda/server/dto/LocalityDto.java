package com.aol.alkuznetsov.panda.server.dto;

import com.aol.alkuznetsov.panda.server.model.Region;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalityDto {
  private Long id;
  private String name;
  private LocalityTypeDto type;
  private Region region;
}
