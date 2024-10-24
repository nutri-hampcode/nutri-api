package com.hampcode.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserMembershipDetailsDTO {
    private Integer id;
    private String name;
    private LocalDate start_date;
    private LocalDate end_date;
    private double price;
    private boolean status;
}
