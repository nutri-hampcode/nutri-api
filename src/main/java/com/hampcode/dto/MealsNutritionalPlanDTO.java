package com.hampcode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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

    private String mealImg;
}