package com.hampcode.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "availability", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"date", "time", "id_doctor"})
})
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="reserved", nullable = false)
    private Boolean reserved;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name= "time" , nullable= false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "id_doctor", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_id_doctor"))
    private Doctor doctor;
}