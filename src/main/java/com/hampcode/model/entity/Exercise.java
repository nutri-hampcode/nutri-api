package com.hampcode.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

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
    private byte[] image;

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