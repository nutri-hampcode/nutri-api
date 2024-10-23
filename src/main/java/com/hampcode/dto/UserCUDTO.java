package com.hampcode.dto;

import com.hampcode.model.entity.Customer.Allergies;

import com.hampcode.model.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCUDTO {
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

    @Positive(message = "Height must be a positive number")
    private Float height;

    @Positive(message = "Weight must be a positive number")
    private Float weight;

    @Min(value = 0, message = "Age must be 0 or greater")
    @Max(value = 150, message = "Age must be 150 or less")
    private Integer age;

    private Allergies allergies;

    private Integer goalId;

    private Integer dietTypeId;

    private Role role;
}
