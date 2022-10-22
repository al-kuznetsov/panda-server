package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.LocalityDto;
import com.aol.alkuznetsov.panda.server.model.Locality;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface LocalityMapper {

  Locality fromDto(LocalityDto dto);

  LocalityDto toDto(Locality entity);
}
