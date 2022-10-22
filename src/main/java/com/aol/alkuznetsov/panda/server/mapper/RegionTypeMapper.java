package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.RegionTypeDto;
import com.aol.alkuznetsov.panda.server.model.RegionType;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface RegionTypeMapper {

  RegionType fromDto(RegionTypeDto dto);

  RegionTypeDto toDto(RegionType entity);
}
