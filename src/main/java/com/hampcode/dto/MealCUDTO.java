package com.hampcode.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MealCUDTO {
    private Integer id;

    @NotBlank(message = "Name of meal is mandatory")
    private String name;

    @NotBlank(message = "Description of meal is mandatory")
    private String description;

    @NotNull(message="ImageURL is mandatory")
    private String imageUrl;

    @NotNull(message = "Calories of meal is mandatory")
    private Integer calories;

    @NotNull(message = "Proteins of meal is mandatory")
    private BigDecimal proteins;

    @NotNull(message = "Carbs of meal is mandatory")
    private BigDecimal carbs;

    @NotNull(message = "Fats of meal is mandatory")
    private BigDecimal fat;
}
