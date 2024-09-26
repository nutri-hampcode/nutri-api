package com.hampcode.service.impl;

import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.Appointment;
import com.hampcode.model.entity.Availability;
import com.hampcode.model.entity.History;
import com.hampcode.repository.AppointmentRepository;
import com.hampcode.repository.AvailabilityRepository;
import com.hampcode.repository.HistoryRepository;
import com.hampcode.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AvailabilityRepository availabilityRepository;
    private final AppointmentRepository appointmentRepository;
    private final HistoryRepository historyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Appointment getOne(Integer id) {
        return appointmentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    @Transactional
    public Appointment create(Appointment appointment) {
        Appointment ap = new Appointment();
        ap.setReason(appointment.getReason());
        Availability availability = availabilityRepository
                .findById(appointment.getAvailability().getId()).orElseThrow(() -> new ResourceNotFoundException("Availability not found"));
        if (availability.getReserved()) {
            throw new IllegalStateException("Selected availability is already reserved.");
        }
        availability.setReserved(true);
        ap.setAvailability(availability);
        History history = historyRepository
                .findById(appointment.getHistory().getId()).orElseThrow(() -> new ResourceNotFoundException("History not found"));
        ap.setHistory(history);
        return appointmentRepository.save(ap);
    }

    @Override
    @Transactional
    public Appointment update(Integer id, Appointment appointment) {
        Appointment existingAppointment = getOne(id);
        existingAppointment.setReason(appointment.getReason());
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Appointment existingAppointment = getOne(id);
        appointmentRepository.delete(existingAppointment);
    }


}