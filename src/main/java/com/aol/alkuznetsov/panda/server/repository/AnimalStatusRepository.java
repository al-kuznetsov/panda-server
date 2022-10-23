package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.AnimalStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalStatusRepository extends JpaRepository<AnimalStatus, Long> {

  Optional<AnimalStatus> findByCode(String code);
}
