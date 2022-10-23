package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.AnimalCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalCriteriaRepository extends JpaRepository<AnimalCriteria, Long> {

}
