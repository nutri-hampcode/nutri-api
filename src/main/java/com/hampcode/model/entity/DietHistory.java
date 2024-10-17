package com.hampcode.model.entity;

import com.hampcode.model.enums.MealType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
