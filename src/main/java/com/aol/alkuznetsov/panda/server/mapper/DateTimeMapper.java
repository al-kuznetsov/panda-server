package com.aol.alkuznetsov.panda.server.mapper;

import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DateTimeMapper {
  default Long fromInstant(Instant instant) {
    return instant.toEpochMilli();
  }

  default Instant toInstant(Long timestamp) {
    return Instant.ofEpochMilli(timestamp);
  }
}
