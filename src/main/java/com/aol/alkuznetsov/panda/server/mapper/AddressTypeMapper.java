package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.AddressTypeDto;
import com.aol.alkuznetsov.panda.server.model.AddressType;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface AddressTypeMapper {

  AddressType fromDto(AddressTypeDto dto);

  AddressTypeDto toDto(AddressType entity);
}
