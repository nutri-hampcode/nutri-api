package com.hampcode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorDTO {
    private Integer id;

    @NotBlank(message = "First Name is mandatory")
    @Size(max = 20, message = "First name can only have a maximum of 20 characters")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    @Size(max = 35, message = "Last name can only have a maximum of 35 characters")
    private String lastName;
}
