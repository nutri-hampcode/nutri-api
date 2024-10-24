package com.hampcode.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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