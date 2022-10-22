package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.BreedDto;
import com.aol.alkuznetsov.panda.server.model.Breed;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface BreedMapper {

  Breed fromDto(BreedDto dto);

  BreedDto toDto(Breed entity);
}
