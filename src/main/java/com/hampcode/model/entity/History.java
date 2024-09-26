package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "histories")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reason;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "history_id")
    private List<Appointment> appointments;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

//    @ManyToOne
//    @JoinColumn(name = "availability_id", nullable = false)
//    private Availability availability;

}