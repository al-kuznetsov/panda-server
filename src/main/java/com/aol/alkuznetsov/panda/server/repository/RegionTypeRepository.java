package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.RegionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionTypeRepository extends JpaRepository<RegionType, Long> {

  Optional<RegionType> findByCode(String code);
}
