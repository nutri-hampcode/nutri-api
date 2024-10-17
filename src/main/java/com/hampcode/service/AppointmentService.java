package com.hampcode.service;

import com.hampcode.dto.AppointmentCreateUpdateDTO;
import com.hampcode.dto.AppointmentDetailsDTO;
import com.hampcode.dto.HistoryDTO;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDetailsDTO> findAll();
    AppointmentDetailsDTO findById(Integer id);
    AppointmentDetailsDTO create(Integer userId, AppointmentCreateUpdateDTO appointmentDTO);
    AppointmentDetailsDTO update(Integer id, AppointmentCreateUpdateDTO appointmentDTO);
    void delete(Integer id);
    List<HistoryDTO> findHistoryByUserId(Integer userId);
}
