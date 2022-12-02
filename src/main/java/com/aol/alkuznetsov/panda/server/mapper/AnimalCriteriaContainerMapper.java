package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AnimalCriteriaContainerDto;
import com.aol.alkuznetsov.panda.server.model.AnimalCriteriaContainer;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class,
    uses = {AnimalMapper.class, AnimalIndicatorsMapper.class})
public interface AnimalCriteriaContainerMapper {

  AnimalCriteriaContainer fromDto(AnimalCriteriaContainerDto dto);

  AnimalCriteriaContainerDto toDto(AnimalCriteriaContainer model);
}
