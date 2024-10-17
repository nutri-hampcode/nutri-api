package com.hampcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "inter_plan_ex")
public class InterPlanEx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_plan_exercise", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_inter_plan_ex_plan_exercise"))
    private PlanExercise planExercise;

    @ManyToOne
    @JoinColumn(name = "id_exercise", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_inter_plan_ex_exercise"))
    private Exercise exercise;
}
