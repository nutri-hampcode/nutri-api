package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reason;

//    @ManyToOne
//    @JoinColumn(name = "availability_id", nullable = false)
//    private Availability availability;

}