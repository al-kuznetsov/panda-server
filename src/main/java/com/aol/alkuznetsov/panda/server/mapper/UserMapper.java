package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import com.aol.alkuznetsov.panda.server.dto.UserDto;
import com.aol.alkuznetsov.panda.server.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface UserMapper {

  User fromDto(UserDto dto);

  UserDto toDto(User entity);
}
