package com.hampcode.dto;

import com.hampcode.model.entity.User.Allergies;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCUDTO {
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
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
}
