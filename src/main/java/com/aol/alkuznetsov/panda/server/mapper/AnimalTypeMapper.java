package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.dto.AnimalTypeDto;
import com.aol.alkuznetsov.panda.server.model.AnimalType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AnimalTypeMapper {

  AnimalType fromDto(AnimalTypeDto dto);

  AnimalTypeDto toDto(AnimalType entity);
}
