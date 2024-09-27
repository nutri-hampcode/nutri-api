package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @OneToMany(mappedBy = "goal")
    private List<PlanExercise> planExercises;
}
