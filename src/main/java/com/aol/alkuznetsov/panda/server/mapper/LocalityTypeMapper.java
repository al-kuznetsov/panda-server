package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.LocalityTypeDto;
import com.aol.alkuznetsov.panda.server.model.LocalityType;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface LocalityTypeMapper {

  LocalityType fromDto(LocalityTypeDto dto);

  LocalityTypeDto toDto(LocalityType entity);
}
