package com.hcLinear.backendTest.repositories;


import com.hcLinear.backendTest.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    long countByTeamId(Long teamId);
    Optional<PlayerEntity> findByTeamIdAndCaptainTrue(Long teamId);
    boolean existsByTeamIdAndCaptainTrue(Long teamId);
}
