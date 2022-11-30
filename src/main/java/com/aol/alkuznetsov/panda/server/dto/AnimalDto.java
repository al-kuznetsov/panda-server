package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalDto {
  private Long id;
  private String name;
  private String birthDate;
  private String description;
  private String fullBio;
  private String imageUrl;
  private Boolean active;
  private Long dateCreated;
  private Long dateUpdated;
  private AnimalTypeDto type;
  private BreedDto breed;
  private AnimalStatusDto status;
  private SexDto sex;
  private AnimalIndicatorsDto indicators;
}
