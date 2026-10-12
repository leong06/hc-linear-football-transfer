package com.hcLinear.backendTest.repositories;

import com.hcLinear.backendTest.entities.TransferRequestEntity;
import com.hcLinear.backendTest.entities.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRequestRepository extends JpaRepository<TransferRequestEntity, Long> {
    List<TransferRequestEntity> findByPlayerId(Long senderId);
    List<TransferRequestEntity> findByTargetTeamId(Long targetTeamId);
    List<TransferRequestEntity> findBySourceTeamId(Long sourceTeamId);
    List<TransferRequestEntity> findByPlayerIdAndStatus(Long playerId, TransferStatus status);
}
