package com.hampcode.dto;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AvailabilityDetailsDTO {
    private Integer id;
    private Boolean reserved;
    private LocalDate date;
    private LocalTime time;
    private String doctorName;
}
