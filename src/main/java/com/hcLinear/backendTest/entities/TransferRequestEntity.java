package com.hcLinear.backendTest.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transfer_requests")
public class TransferRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdAt;
    private LocalDate expiresAt;

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

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    public TransferRequestEntity() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDate expiresAt) {
        this.expiresAt = expiresAt;
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

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }
}
