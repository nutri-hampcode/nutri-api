package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "calories", precision = 10, scale = 2)
    private Integer calories;

    @Column(name = "proteins", precision = 10, scale = 2)
    private BigDecimal proteins;

    @Column(name = "carbs", precision = 10, scale = 2)
    private BigDecimal carbs;

    @Column(name = "fat", precision = 10, scale = 2)
    private BigDecimal fat;

    @ManyToOne
    @JoinColumn(name = "id_meal", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_id_diettype"))
    private DietType dietType;
}
