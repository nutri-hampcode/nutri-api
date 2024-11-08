package com.hampcode.dto;

import lombok.Data;

@Data
public class MealsNutritionalPlanDetailsDTO {
    private Integer id;
    private String weekDay;
    private String mealType;
    private String nutritionalPlan;
    private MealDetailsDTO meal;
    private String mealImg;
}