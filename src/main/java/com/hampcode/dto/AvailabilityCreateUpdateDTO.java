package com.hampcode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AvailabilityCreateUpdateDTO {
    private Integer id;

    @NotNull(message = "State of reservation is mandatory")
    @BooleanFlag
    private Boolean reserved;

    @NotNull(message = "Available date is mandatory")
    private LocalDate date;

    @NotNull(message = "Available time is mandatory")
    private LocalTime time;

}
