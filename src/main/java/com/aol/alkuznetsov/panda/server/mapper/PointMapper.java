package com.aol.alkuznetsov.panda.server.mapper;

import static com.aol.alkuznetsov.panda.server.constant.GisConst.LOCAL_SRID;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.PointDto;
import org.geolatte.geom.C2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface PointMapper {

  default Point<C2D> fromDto(PointDto dto) {
    return DSL.point(LOCAL_SRID, DSL.c(dto.getX(), dto.getY()));
  }

  default PointDto toDto(Point<C2D> entity) {
    return PointDto.builder()
        .type("Point")
        .x(entity.getPosition().getX())
        .y(entity.getPosition().getY())
        .build();
  }
}
