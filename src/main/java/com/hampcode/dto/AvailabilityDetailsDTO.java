package com.hampcode.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AvailabilityDetailsDTO {
    private Boolean reserved;
    private LocalDate date;
    private LocalTime time;
    private String doctorName;
}
