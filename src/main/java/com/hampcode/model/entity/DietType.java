package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "diet_types")
public class DietType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;
}
