package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

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