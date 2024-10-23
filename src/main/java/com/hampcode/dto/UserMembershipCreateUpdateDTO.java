package com.hampcode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data

public class UserMembershipCreateUpdateDTO {

    @NotBlank(message = "Start date is mandatory")
    private LocalDate start_date;

    private LocalDate end_date;

    @NotNull(message = "Membership id is mandatory")
    private int membership_id;

    public void setEnd_date(LocalDate start_date) {
        this.start_date = start_date;
        this.end_date = start_date.plusMonths(1);
    }
}
