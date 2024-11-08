package com.hampcode.model.entity;

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
@Table(name = "meals_nutritional_plan")
public class MealsNutritionalPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "week_day", nullable = false)
    private String weekDay;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @ManyToOne
    @JoinColumn(name = "nutritional_plan_id", referencedColumnName = "id", nullable = false)
    private NutritionalPlan nutritionalPlan;

    @ManyToOne
    @JoinColumn(name = "meal_id", referencedColumnName = "id", nullable = false)
    private Meal meal;

    @Column(name = "image")
    private String mealImg;
}