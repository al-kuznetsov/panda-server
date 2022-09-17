package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.dto.AnimalDto;
import com.aol.alkuznetsov.panda.server.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = {DateTimeMapper.class, AnimalStatusMapper.class})
public interface AnimalMapper {

  @Mapping(target = "animalStatus", source = "animalStatusDto")
  Animal fromDto(AnimalDto dto);

  @Mapping(target = "animalStatusDto", source = "animalStatus")
  AnimalDto toDto(Animal entity);
}
