package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "memberships")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Name name = Name.BASIC;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double price;

    public enum Name {
        BASIC, PREMIUM
    }
}
