package com.hampcode.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="link_video",unique = true, nullable = false)
    private String linkVideo;

    @Column(name = "image")
    private String image;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_goal",nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_goal"))
    @JsonIgnoreProperties("exercises")
    private Goal goal;

    @OneToMany(mappedBy = "exercise")
    @JsonIgnoreProperties("exercises")
    private List<Tip> Tips;
}