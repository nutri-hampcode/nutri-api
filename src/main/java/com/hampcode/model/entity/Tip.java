package com.hampcode.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tip")
public class Tip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "id_exercise", nullable = false, referencedColumnName = "id",foreignKey = @ForeignKey(name = "FK_exercise"))
    @JsonIgnoreProperties("tip")
    private Exercise exercise;
}