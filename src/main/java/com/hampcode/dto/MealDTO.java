package com.hampcode.dto;

import java.math.BigDecimal;

import com.hampcode.model.entity.DietType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MealDTO {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Calories cannot be null")
    private Integer calories; // Agregado

    @NotNull(message = "Proteins cannot be null")
    private BigDecimal proteins;

    @NotNull(message = "Carbs cannot be null")
    private BigDecimal carbs;

    @NotNull(message = "Fat cannot be null")
    private BigDecimal fat;

    @NotNull(message = "Diet type cannot be null") // Agregado
    private DietType dietType; // Asegúrate de que DietType esté definido

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCalories() {
        return calories; // Agregado
    }

    public void setCalories(Integer calories) { // Agregado
        this.calories = calories;
    }

    public BigDecimal getProteins() {
        return proteins;
    }

    public void setProteins(BigDecimal proteins) {
        this.proteins = proteins;
    }

    public BigDecimal getCarbs() {
        return carbs;
    }

    public void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public DietType getDietType() { // Agregado
        return dietType;
    }

    public void setDietType(DietType dietType) { // Agregado
        this.dietType = dietType;
    }
}
