package com.hampcode.dto;

import com.hampcode.model.entity.Doctor;
import com.hampcode.model.entity.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NutritionalPlanDTO {

    @NotNull(message = "Type cannot be null")
    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Doctor cannot be null")
    private Doctor doctor;

    @NotNull(message = "User cannot be null")
    private User user;

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}