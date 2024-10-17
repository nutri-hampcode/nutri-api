package com.hampcode.model.entity;

import java.time.LocalDate;

import com.hampcode.model.enums.MealType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "diet_histories")
public class DietHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "meal_type")
    private MealType mealType;

    @Column(name = "portion_quantity")
    private Float portion_quantity;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_id_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_meal", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_id_meal"))
    private Meal meal;
}
