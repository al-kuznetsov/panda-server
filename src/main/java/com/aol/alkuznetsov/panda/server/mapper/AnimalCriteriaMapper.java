package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AnimalCriteriaDto;
import com.aol.alkuznetsov.panda.server.model.AnimalCriteria;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface AnimalCriteriaMapper {
  AnimalCriteria fromDto(AnimalCriteriaDto dto);

  AnimalCriteriaDto toDto(AnimalCriteria entity);
}
