package com.hampcode.dto;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class MealDetailsDTO {
    private String name;
    private String description;
    private Integer calories;
    private BigDecimal proteins;
    private BigDecimal carbs;
    private BigDecimal fat;
    private String dietType;
    private String image;
}
