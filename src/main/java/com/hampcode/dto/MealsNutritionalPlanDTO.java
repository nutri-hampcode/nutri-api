package com.hampcode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MealsNutritionalPlanDTO {

    @NotNull(message = "Week day cannot be null")
    @NotEmpty(message = "Week day cannot be empty")
    private String weekDay;

    @NotNull(message = "Meal type cannot be null")
    @NotEmpty(message = "Meal type cannot be empty")
    private String mealType;

    @NotNull(message = "Nutritional Plan ID cannot be null")
    private Integer nutritionalPlanId;

    @NotNull(message = "Meal ID cannot be null")
    private Integer mealId;

    // Getters and Setters
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Integer getNutritionalPlanId() {
        return nutritionalPlanId;
    }

    public void setNutritionalPlanId(Integer nutritionalPlanId) {
        this.nutritionalPlanId = nutritionalPlanId;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }
}