package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalCriteriaDto {
  private Long id;
  private Integer stress;
  private Integer sickness;
  private Integer trauma;
  private Integer mobility;
  private Integer tameness;
}
