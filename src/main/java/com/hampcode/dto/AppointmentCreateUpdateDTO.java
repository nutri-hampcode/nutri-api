package com.hampcode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class AppointmentCreateUpdateDTO {
    @NotBlank(message = "Reason is mandatory")
    private String reason;
    @NotNull(message = "Availability is mandatory")
    private Integer availabilityId;
}