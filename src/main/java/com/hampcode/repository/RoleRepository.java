package com.hampcode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hampcode.model.entity.Role;
import com.hampcode.model.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //Buscar un rol por su nombre (usando el enum)
    Optional<Role> findByName(ERole name);
}
