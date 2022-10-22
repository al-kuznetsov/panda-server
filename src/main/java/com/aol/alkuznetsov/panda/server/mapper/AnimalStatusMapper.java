package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AnimalStatusDto;
import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface AnimalStatusMapper {

  AnimalStatus fromDto(AnimalStatusDto dto);

  AnimalStatusDto toDto(AnimalStatus entity);
}
