package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.dto.AnimalStatusDto;
import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AnimalStatusMapper {

  AnimalStatus fromDto(AnimalStatusDto dto);

  AnimalStatusDto toDto(AnimalStatus entity);
}
