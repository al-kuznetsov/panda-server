package com.aol.alkuznetsov.panda.server.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;

@Data
@Builder
public class SpotDto {
  private UUID id;
  private Point<C2D> point;
}
