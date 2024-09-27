package com.hampcode.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="link_video",unique = true, nullable = false)
    private String linkVideo;

    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "tips", nullable = false, columnDefinition = "TEXT")
    private String tips;

    @OneToMany(mappedBy = "exercise")
    private Set<InterPlanEx> inteplanExs = new HashSet<>();
}
