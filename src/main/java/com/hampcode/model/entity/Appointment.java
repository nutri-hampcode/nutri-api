package com.hampcode.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reason;

    @OneToOne
    @JoinColumn(name = "availability_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_id_availability"))
    private Availability availability;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_id_user"))
    @JsonBackReference
    private User user;

}