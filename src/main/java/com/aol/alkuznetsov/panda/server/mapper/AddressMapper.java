package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AddressDto;
import com.aol.alkuznetsov.panda.server.model.Address;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface AddressMapper {

  Address fromDto(AddressDto dto);

  AddressDto toDto(Address entity);
}
