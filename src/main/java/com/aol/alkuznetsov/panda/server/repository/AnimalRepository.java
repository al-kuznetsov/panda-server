package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.Animal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

  Page<Animal> findAllByTypeCode(String code, Pageable pageable);

  Page<Animal> findAllByNameOrDescriptionContainingIgnoreCase(
      String nameSearchKey, String descriptionSearchKey, Pageable pageable);

  @Query("select a from Animal a where a.id in :ids")
  List<Animal> findAllByIdList(@Param("ids") List<Long> ids);
}
