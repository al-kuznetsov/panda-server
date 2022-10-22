package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AnimalTypeDto;
import com.aol.alkuznetsov.panda.server.model.AnimalType;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface AnimalTypeMapper {

  AnimalType fromDto(AnimalTypeDto dto);

  AnimalTypeDto toDto(AnimalType entity);
}
