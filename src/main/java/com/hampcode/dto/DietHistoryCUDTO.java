package com.hampcode.dto;

import com.hampcode.model.entity.Meal;
import com.hampcode.model.entity.User;
import com.hampcode.model.enums.MealType;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

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
