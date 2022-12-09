package com.aol.alkuznetsov.panda.server.mapper;

import com.aol.alkuznetsov.panda.server.config.MapperCentralConfig;
import java.time.Instant;
import org.mapstruct.Mapper;

@Mapper(config = MapperCentralConfig.class)
public interface DateTimeMapper {
  default Long fromInstant(Instant instant) {
    return instant != null ? instant.toEpochMilli() : null;
  }

  default Instant toInstant(Long timestamp) {
    return Instant.ofEpochMilli(timestamp);
  }
}
