package com.hampcode.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "proteins", precision = 10, scale = 2)
    private BigDecimal proteins;

    @Column(name = "carbs", precision = 10, scale = 2)
    private BigDecimal carbs;

    @Column(name = "fat", precision = 10, scale = 2)
    private BigDecimal fat;

    @ManyToOne
    @JoinColumn(name = "diet_type_id", referencedColumnName = "id")
    private DietType dietType;
}