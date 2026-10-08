package com.hcLinear.backendTest.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;

    @Enumerated(EnumType.STRING)
    private Position position;

    private int shirt_number;
    private Date birth_date;
    private int market_value;

    public PlayerEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getShirt_number() {
        return shirt_number;
    }

    public void setShirt_number(int shirt_number) {
        this.shirt_number = shirt_number;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public int getMarket_value() {
        return market_value;
    }

    public void setMarket_value(int market_value) {
        this.market_value = market_value;
    }

    public PlayerEntity(Long id, String first_name, String last_name, Position position, int shirt_number, Date birth_date, int market_value) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.shirt_number = shirt_number;
        this.birth_date = birth_date;
        this.market_value = market_value;
    }
}
