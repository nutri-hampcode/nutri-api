package com.hampcode.dto;

import com.hampcode.model.entity.Doctor;
import com.hampcode.model.entity.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NutritionalPlanDTO {

    @NotNull(message = "Type cannot be null")
    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Doctor cannot be null")
    private Doctor doctor;

    @NotNull(message = "User cannot be null")
    private User user;
}