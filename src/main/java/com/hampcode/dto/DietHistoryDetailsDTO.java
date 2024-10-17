package com.hampcode.dto;

import com.hampcode.model.enums.MealType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DietHistoryDetailsDTO {
    private Integer id;
    private LocalDate date;
    private Float portion_quantity;
    private String user_name;
    private MealType meal_type;
    private MealDetailsDTO meal;
}
