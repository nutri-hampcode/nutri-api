package com.hampcode.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "goal")
public class Goal {

    public enum GoalType {
        BAJAR_PESO,
        SUBIR_PESO,
        MANTENER_PESO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private GoalType goalType; // Este es el campo que usas en GoalDTO

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("goal")
    private List<Exercise> exercises; // Conviene usar "exercises" en minúscula

    // Lombok generará automáticamente el método getGoalType()
}