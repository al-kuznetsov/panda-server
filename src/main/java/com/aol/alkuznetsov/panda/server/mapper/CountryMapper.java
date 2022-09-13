package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.dto.CountryDto;
import com.aol.alkuznetsov.panda.server.model.Country;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CountryMapper {
  Country fromDto(CountryDto countryDto);

  CountryDto toDto(Country country);
}
