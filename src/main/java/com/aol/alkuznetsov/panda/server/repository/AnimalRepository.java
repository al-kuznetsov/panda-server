package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

  Page<Animal> findAllByTypeCode(@Param("code") String code, Pageable pageable);
}
