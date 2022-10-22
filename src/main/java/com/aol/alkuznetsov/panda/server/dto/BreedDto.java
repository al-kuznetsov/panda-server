package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BreedDto {
  private Long id;
  private String name;
  private String description;
  private Boolean valuable;
  private AnimalTypeDto type;
  private String imageUrl;
  private Float averageBodyMass;
  private Float averageHeight;
  private Float averageLength;
  private Integer averageTameness;
}
