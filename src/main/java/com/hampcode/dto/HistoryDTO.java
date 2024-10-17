package com.hampcode.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class HistoryDTO {
    private LocalDate date;
    private LocalTime time;
    private String doctorName;
    private String reason;
}
