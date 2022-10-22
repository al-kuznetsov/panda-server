package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.RegionDto;
import com.aol.alkuznetsov.panda.server.model.Region;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface RegionMapper {

  Region fromDto(RegionDto dto);

  RegionDto toDto(Region entity);
}
