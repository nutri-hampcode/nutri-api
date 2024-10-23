package com.hampcode.dto;

import java.time.LocalDate;

import com.hampcode.model.enums.MealType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DietHistoryCUDTO {
    private Integer id;

    @NotNull(message = "date is mandatory")
    private LocalDate date;

    @NotNull(message = "meal type is mandatory")
    private MealType mealType;

    @NotNull(message = "portion quantity is mandatory")
    private Float portion_quantity;
}
