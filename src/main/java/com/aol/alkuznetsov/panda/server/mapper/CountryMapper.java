package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.CountryDto;
import com.aol.alkuznetsov.panda.server.model.Country;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CountryMapper {
  Country fromDto(CountryDto dto);

  CountryDto toDto(Country entity);
}
