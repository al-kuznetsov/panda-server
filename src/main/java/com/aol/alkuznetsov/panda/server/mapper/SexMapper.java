package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.SexDto;
import com.aol.alkuznetsov.panda.server.model.Sex;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface SexMapper {
  
  Sex fromDto(SexDto dto);
  
  SexDto toDto(Sex entity);

}
