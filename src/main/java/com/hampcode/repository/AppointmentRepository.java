package com.hampcode.repository;

import com.hampcode.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a from Appointment a where a.user.id = :id_user")
    Optional<List<Appointment>> findByUserId(@Param("id_user") Integer id_user);
}

