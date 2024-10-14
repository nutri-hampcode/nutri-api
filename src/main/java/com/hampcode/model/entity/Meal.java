package com.hampcode.model.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "diet_type")
    private DietType dietType;
}