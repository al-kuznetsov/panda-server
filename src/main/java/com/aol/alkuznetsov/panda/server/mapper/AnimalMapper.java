package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = {DateTimeMapper.class, AnimalStatusMapper.class, AnimalTypeMapper.class})
public interface AnimalMapper {
  @Mapping(target = "type", source = "typeDto")
  @Mapping(target = "status", source = "statusDto")
  Animal fromDto(AnimalDto dto);

  @Mapping(target = "typeDto", source = "type")
  @Mapping(target = "statusDto", source = "status")
  AnimalDto toDto(Animal entity);
}
