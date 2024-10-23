package com.hampcode.mapper;

import org.springframework.stereotype.Component;

import com.hampcode.dto.AppointmentCreateUpdateDTO;
import com.hampcode.dto.AppointmentDetailsDTO;
import com.hampcode.dto.AvailabilityDetailsDTO;
import com.hampcode.dto.HistoryDTO;
import com.hampcode.model.entity.Appointment;
import com.hampcode.model.entity.Availability;

@Component
public class AppointmentMapper {

    public AppointmentDetailsDTO toDetailsDTO(Appointment appointment) {
        AppointmentDetailsDTO dto = new AppointmentDetailsDTO();
        dto.setId(appointment.getId());
        dto.setReason(appointment.getReason());
        dto.setId_user(appointment.getUser().getId());

        AvailabilityDetailsDTO availabilityDTO = new AvailabilityDetailsDTO();
        Availability availability = appointment.getAvailability();
        availabilityDTO.setId(availability.getId());
        availabilityDTO.setDate(availability.getDate());
        availabilityDTO.setTime(availability.getTime());
        availabilityDTO.setDoctorName(availability.getDoctor().getFirstName());
        availabilityDTO.setDoctorName(availability.getDoctor().getLastName());
        availabilityDTO.setReserved(availability.getReserved());

        dto.setAvailability(availabilityDTO);
        return dto;
    }

    public Appointment toEntity(AppointmentCreateUpdateDTO appointmentCreateUpdateDTO) {
        Appointment appointment = new Appointment();
        appointment.setReason(appointmentCreateUpdateDTO.getReason());

        return appointment;
    }

    public HistoryDTO toHistoryDTO(Appointment appointment) {
        HistoryDTO dto = new HistoryDTO();
        dto.setDate(appointment.getAvailability().getDate());
        dto.setTime(appointment.getAvailability().getTime());
        dto.setDoctorName(appointment.getAvailability().getDoctor().getFirstName());
        dto.setDoctorName(appointment.getAvailability().getDoctor().getLastName());
        dto.setReason(appointment.getReason());
        return dto;
    }
}