package com.hampcode.service;

import com.hampcode.dto.AvailabilityCreateUpdateDTO;
import com.hampcode.dto.AvailabilityDetailsDTO;
import com.hampcode.model.entity.Availability;

import java.util.List;

public interface AvailabilityService {
    List<AvailabilityDetailsDTO> findAll();
    List<AvailabilityDetailsDTO> findDoctorAvailability(Integer id_doctor);
    AvailabilityDetailsDTO findById(Integer id);
    AvailabilityDetailsDTO create(Integer id_doctor, AvailabilityCreateUpdateDTO availabilityCreateDTO);
    AvailabilityDetailsDTO update(Integer id, AvailabilityCreateUpdateDTO availabilityUpdateDTO);
    void delete(Integer id);
}
