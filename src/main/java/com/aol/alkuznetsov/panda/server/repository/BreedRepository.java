package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.Breed;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {

  Optional<Breed> findByName(String name);
}
