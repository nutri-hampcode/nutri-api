package com.hampcode.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

}
