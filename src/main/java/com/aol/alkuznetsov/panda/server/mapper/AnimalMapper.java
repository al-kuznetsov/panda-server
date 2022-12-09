package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    config = MapperCentralConfig.class,
    uses = {
      DateTimeMapper.class,
      AnimalStatusMapper.class,
      AnimalTypeMapper.class,
      AnimalIndicatorsMapper.class,
      SexMapper.class
    })
public interface AnimalMapper {

  @Mapping(target = "dateCreated", ignore = true)
  @Mapping(target = "dateUpdated", ignore = true)
  Animal fromDto(AnimalDto dto);

  AnimalDto toDto(Animal entity);
}
