package com.aol.alkuznetsov.panda.server.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpotDto {
  private UUID id;
  private PointDto point;
}
