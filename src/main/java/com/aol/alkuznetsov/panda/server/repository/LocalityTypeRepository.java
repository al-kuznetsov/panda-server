package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.LocalityType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalityTypeRepository extends JpaRepository<LocalityType, Long> {

  Optional<LocalityType> findByCode(String code);
}
