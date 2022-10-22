package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.Spot;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, UUID> {}
