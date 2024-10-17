package com.hampcode.dto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MealDetailsDTO {
    private String name;
    private String description;
    private Integer calories;
    private BigDecimal proteins;
    private BigDecimal carbs;
    private BigDecimal fat;
    private String dietType;
}
