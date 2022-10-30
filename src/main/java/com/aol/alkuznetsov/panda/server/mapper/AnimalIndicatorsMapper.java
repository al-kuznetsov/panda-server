package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AnimalIndicatorsDto;
import com.aol.alkuznetsov.panda.server.model.AnimalIndicators;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface AnimalIndicatorsMapper {
  AnimalIndicators fromDto(AnimalIndicatorsDto dto);

  AnimalIndicatorsDto toDto(AnimalIndicators entity);
}
