package com.hcLinear.backendTest.repositories;

import com.hcLinear.backendTest.entities.TransferDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface TransferDocumentRepository extends JpaRepository<TransferDocumentEntity, Long> {
    @Query(value = "SELECT nextval('seq_document_number')", nativeQuery = true)
    Long nextDocumentNumber();

    Optional<TransferDocumentEntity> findByDocumentNumber(String documentNumber);

}
