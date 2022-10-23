package com.aol.alkuznetsov.panda.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointDto {
  private String type;
  private Double x;
  private Double y;
}
