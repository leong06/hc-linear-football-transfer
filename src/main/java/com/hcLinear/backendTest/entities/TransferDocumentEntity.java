package com.hcLinear.backendTest.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_documents")
public class TransferDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentNumber;

    @OneToOne
    @JoinColumn(name = "transfer_request_id")
    private TransferRequestEntity transferRequest;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @ManyToOne
    @JoinColumn(name = "source_team_id")
    private TeamEntity sourceTeam;

    @ManyToOne
    @JoinColumn(name = "target_team_id")
    private TeamEntity targetTeam;

    private BigDecimal amount;

    public TransferDocumentEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public TransferRequestEntity getTransferRequest() {
        return transferRequest;
    }

    public void setTransferRequest(TransferRequestEntity transferRequest) {
        this.transferRequest = transferRequest;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public TeamEntity getSourceTeam() {
        return sourceTeam;
    }

    public void setSourceTeam(TeamEntity sourceTeam) {
        this.sourceTeam = sourceTeam;
    }

    public TeamEntity getTargetTeam() {
        return targetTeam;
    }

    public void setTargetTeam(TeamEntity targetTeam) {
        this.targetTeam = targetTeam;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
