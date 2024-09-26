package com.hampcode.service;

import com.hampcode.model.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAll();
    Appointment getOne(Integer id);
    Appointment create(Appointment appointment);
    Appointment update(Integer id, Appointment appointment);
    void delete(Integer id);
}
