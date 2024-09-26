package com.hampcode.service.impl;

import com.hampcode.model.entity.Appointment;
import com.hampcode.repository.AppointmentRepository;
import com.hampcode.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Appointment getOne(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    @Transactional
    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public Appointment update(Integer id, Appointment appointment) {
        Appointment existingAppointment = getOne(id);
        // Update fields as necessary
        existingAppointment.setType(appointment.getType());
        // Add other fields as needed
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        appointmentRepository.deleteById(id);
    }
}