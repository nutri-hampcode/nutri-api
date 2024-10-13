package com.hampcode.dto;

import lombok.Data;

@Data
public class AppointmentDetailsDTO {
    private Integer id;
    private Integer id_user;
    private String reason;
    private AvailabilityDetailsDTO availability;
}
