package com.hampcode.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hampcode.dto.AppointmentCreateUpdateDTO;
import com.hampcode.dto.AppointmentDetailsDTO;
import com.hampcode.dto.HistoryDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.AppointmentMapper;
import com.hampcode.model.entity.Appointment;
import com.hampcode.model.entity.Availability;
import com.hampcode.model.entity.User;
import com.hampcode.repository.AppointmentRepository;
import com.hampcode.repository.AvailabilityRepository;
import com.hampcode.repository.UserRepository;
import com.hampcode.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final AppointmentMapper appointmentMapper;
    private final AvailabilityRepository availabilityRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AppointmentDetailsDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toDetailsDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public AppointmentDetailsDTO findById(Integer id) {
        Appointment appointment = appointmentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        return appointmentMapper.toDetailsDTO(appointment);
    }

    @Transactional
    @Override
    public AppointmentDetailsDTO create(Integer userId, AppointmentCreateUpdateDTO appointmentDTO) {
        // Buscar el usuario por ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Mapear el DTO a la entidad Appointment
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);

        // Buscar un availabilityid
        Availability availability = availabilityRepository.findById(appointmentDTO.getAvailabilityId())
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + appointmentDTO.getAvailabilityId()));

        // Asociar el usuario a la cita
        appointment.setUser(user);

        // Asegurar que el availability no esté reservado
        if (availability.getReserved()) {
            throw new ResourceNotFoundException("Availability is already reserved");
        }

        // Marcar la disponibilidad como reservada
        availability.setReserved(true);

        // Asociar el availability a la cita
        appointment.setAvailability(availability);

        // Guardar la cita en la base de datos
        appointment = appointmentRepository.save(appointment);

        // Devolver el AppointmentDetailsDTO
        return appointmentMapper.toDetailsDTO(appointment);
    }


    @Transactional
    @Override
    public AppointmentDetailsDTO update(Integer id, AppointmentCreateUpdateDTO appointmentDTO) {
        Appointment appointment = appointmentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));

        appointment.setReason(appointmentDTO.getReason());
        appointmentRepository.save(appointment);

        return appointmentMapper.toDetailsDTO(appointment);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Appointment appointment = appointmentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        appointment.getAvailability().setReserved(false);
        appointmentRepository.save(appointment);
        appointmentRepository.delete(appointment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<HistoryDTO> findHistoryByUserId(Integer userId) {
        Optional<List<Appointment>> appointments = appointmentRepository.findByUserId(userId);

        return appointments.orElseThrow(() -> new ResourceNotFoundException("No appointments found for user id: " + userId))
                .stream()
                .map(appointmentMapper::toHistoryDTO)
                .collect(Collectors.toList());
    }


}