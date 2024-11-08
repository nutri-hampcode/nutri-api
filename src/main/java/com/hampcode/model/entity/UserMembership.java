package com.hampcode.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "userMemberships")
@Data
public class UserMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_id_user"))
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_membership", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_id_membership"))
    private Membership membership;

    @Column(name = "start_date", nullable = false, columnDefinition = "DATE")
    private LocalDate start_date;

    @Column(name = "end_date", nullable = false, columnDefinition = "DATE")
    private LocalDate end_date;

    @Column(name = "status", nullable = false)
    private boolean status = false;
}
