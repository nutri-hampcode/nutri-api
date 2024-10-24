package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="height", nullable = true)
    private Float height;

    @Column(name="weight", nullable = true)
    private Float weight;

    @Column(name="age", nullable = true)
    private Integer age;

    @Column(name="allergies", nullable = true)
    @Enumerated(EnumType.STRING)
    private Allergies allergies;

    @OneToOne
    @JoinColumn(name = "id_goal", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_id_goal"))
    private Goal goal;

    @OneToOne
    @JoinColumn(name = "id_diet_type", referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "FK_id_diet_types"))
    private DietType dietType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public enum Allergies{
        NONE, PEANUTS, DAIRY, GLUTEN, EGGS, SOY, SHELLFISH
    }
}