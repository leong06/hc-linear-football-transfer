package com.hcLinear.backendTest.repositories;

import com.hcLinear.backendTest.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    boolean existsByName(String name);
}
