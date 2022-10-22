package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.model.Animal;
import org.mapstruct.Mapper;

@Mapper(
    config = MapperCentralConfig.class,
    uses = {DateTimeMapper.class, AnimalStatusMapper.class, AnimalTypeMapper.class})
public interface AnimalMapper {

  Animal fromDto(AnimalDto dto);

  AnimalDto toDto(Animal entity);
}
