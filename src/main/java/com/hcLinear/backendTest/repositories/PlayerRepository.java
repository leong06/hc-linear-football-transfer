package com.hcLinear.backendTest.repositories;


import com.hcLinear.backendTest.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

}
