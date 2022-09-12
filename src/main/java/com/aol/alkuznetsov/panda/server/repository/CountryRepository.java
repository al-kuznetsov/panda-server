package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {}
