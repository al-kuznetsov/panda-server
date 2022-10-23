package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.SpotDto;
import com.aol.alkuznetsov.panda.server.model.Spot;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class, uses = PointMapper.class)
public interface SpotMapper {

  Spot fromDto(SpotDto dto);

  SpotDto toDto(Spot entity);
}
