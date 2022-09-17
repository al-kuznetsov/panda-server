package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalStatusDto {
  private Long id;
  private String code;
  private String name;
  private String description;

}
