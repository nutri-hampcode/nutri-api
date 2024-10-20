package com.hampcode.repository;

import com.hampcode.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByFirstNameAndLastName(String firstName, String lastName);
}
