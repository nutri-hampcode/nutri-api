package com.hampcode.model.entity;


import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "user2",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private Role role;

}