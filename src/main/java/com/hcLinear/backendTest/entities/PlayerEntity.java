package com.hcLinear.backendTest.entities;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.util.Date;
import java.time.LocalDate;

@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position;

    private int shirtNumber;
    private LocalDate birthDate;
    private int marketValue;

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public PlayerEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirt_number) {
        this.shirtNumber = shirt_number;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birth_date) {
        this.birthDate = birth_date;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(int market_value) {
        this.marketValue = market_value;
    }

    public PlayerEntity(Long id, String firstName, String lastName, Position position, int shirtNumber, LocalDate birthDate, int marketValue) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.shirtNumber = shirtNumber;
        this.birthDate = birthDate;
        this.marketValue = marketValue;
    }
}
