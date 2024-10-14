package com.hampcode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NutritionalPlanDTO {

    @NotNull(message = "Type cannot be null")
    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Doctor cannot be null")
    private Integer doctorId; // Para referenciar al doctor

    @NotNull(message = "User cannot be null")
    private Integer userId; // Para referenciar al usuario

    // Getters y Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}