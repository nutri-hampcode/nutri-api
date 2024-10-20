package com.hampcode.repository;

import com.hampcode.model.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
    @Query("SELECT a from Availability a where a.doctor.id = :id_doctor ")
    Optional<List<Availability>> findByDoctor(@Param("id_doctor") Integer id_doctor);
}
