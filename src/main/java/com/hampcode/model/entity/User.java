package com.hampcode.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "user2",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="email", nullable = false)
    private String email;

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

    public enum Allergies{
        NONE, PEANUTS, DAIRY, GLUTEN, EGGS, SOY, SHELLFISH
    }
}