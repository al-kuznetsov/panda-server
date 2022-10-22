package com.aol.alkuznetsov.panda.server.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.geolatte.geom.C2D;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GisConst {
  public static final CoordinateReferenceSystem<C2D> LOCAL_SRID =
      CoordinateReferenceSystems.WEB_MERCATOR;
}
