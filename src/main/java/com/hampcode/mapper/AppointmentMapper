package com.hampcode.mapper;

import com.hampcode.dto.AppointmentDTO;
import com.hampcode.model.entity.Appointment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    private final ModelMapper modelMapper;
    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public AppointmentDTO toDTO(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);
    }
    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        return modelMapper.map(appointmentDTO, Appointment.class);
    }

}
