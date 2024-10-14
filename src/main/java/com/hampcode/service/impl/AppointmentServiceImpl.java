package com.hampcode.service.impl;

import com.hampcode.dto.AppointmentDTO;
import com.hampcode.dto.AvailabilityDetailsDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.AppointmentMapper;
import com.hampcode.model.entity.Appointment;
import com.hampcode.model.entity.Availability;
import com.hampcode.model.entity.History;
import com.hampcode.repository.AppointmentRepository;
import com.hampcode.repository.AvailabilityRepository;
import com.hampcode.repository.HistoryRepository;
import com.hampcode.service.AppointmentService;
import com.hampcode.mapper.AvailabilityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AvailabilityRepository availabilityRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AvailabilityMapper availabilityMapper;
    private final HistoryRepository historyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAll() {
        List<Appointment> app = appointmentRepository.findAll();
        return app.stream()
                .map(appointmentMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentDTO getOne(Integer id) {
        Appointment app = appointmentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        return appointmentMapper.toDTO(app);
    }

    @Override
    @Transactional
    public AppointmentDTO create(Integer historyId, AppointmentDTO appointmentDTO) {
        Availability avai =availabilityRepository.findById(appointmentDTO.getAvailability().getId())
                .orElseThrow(() ->
                    new RuntimeException("Availability not found"));
        Appointment app = appointmentMapper.toEntity(appointmentDTO);
        if (avai.getReserved()) {
            throw new IllegalStateException("Selected availability is already reserved.");
        }
        avai.setReserved(true);
        app.setAvailability(avai);

        History history = historyRepository
                .findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("History not found"));
        app.setHistory(history);

        Appointment savedAppointment = appointmentRepository.save(app);

        return appointmentMapper.toDTO(savedAppointment);
    }

    @Override
    @Transactional
    public AppointmentDTO update(Integer id, AppointmentDTO appointmentDTO) {
        Appointment existingAppointment = appointmentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        Availability availa= availabilityRepository.findById(appointmentDTO.getAvailability().getId()).orElseThrow(() -> new ResourceNotFoundException("Availability not found"));

        existingAppointment.setReason(appointmentDTO.getReason());
        existingAppointment.setAvailability(availa);

        return appointmentMapper.toDTO(appointmentRepository.save(existingAppointment));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        appointmentRepository.deleteById(Long.valueOf(id));
    }


}
