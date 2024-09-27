package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "plan_exercise")
public class PlanExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_goal", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_exercise_plan_goal"))
    private Goal goal;

    @OneToMany(mappedBy = "planExercise")
    private Set<InterPlanEx> interPlanExs = new HashSet<>();
}
