package com.hampcode.model.entity;

<<<<<<< HEAD
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
=======
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
>>>>>>> origin/feature/crud-diethistory

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

<<<<<<< HEAD
    @Column(name = "calories")
=======
    @Column(name = "calories", precision = 10, scale = 2)
>>>>>>> origin/feature/crud-diethistory
    private Integer calories;

    @Column(name = "proteins", precision = 10, scale = 2)
    private BigDecimal proteins;

    @Column(name = "carbs", precision = 10, scale = 2)
    private BigDecimal carbs;

    @Column(name = "fat", precision = 10, scale = 2)
    private BigDecimal fat;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "diet_type_id", referencedColumnName = "id")
    private DietType dietType;
}
=======
    @JoinColumn(name = "id_meal", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_id_diettype"))
    private DietType dietType;
}
>>>>>>> origin/feature/crud-diethistory
