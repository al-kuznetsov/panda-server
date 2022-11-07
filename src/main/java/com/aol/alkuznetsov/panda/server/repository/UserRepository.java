package com.aol.alkuznetsov.panda.server.repository;

import com.aol.alkuznetsov.panda.server.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByNameOrJobTitleContainingIgnoreCase(
      String nameSearchKey, String jobTitleSearchKey);
}
