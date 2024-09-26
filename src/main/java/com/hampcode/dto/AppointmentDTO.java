package com.hampcode.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
public class AppointmentDTO {
    private String doctorName;
    private LocalDate date;
    private LocalTime time;
    private Boolean reserved;
    private String type;
}
