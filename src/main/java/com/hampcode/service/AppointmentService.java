package com.hampcode.service;

import com.hampcode.dto.AppointmentDTO;
import com.hampcode.model.entity.History;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDTO> findAll();
    AppointmentDTO getOne(Integer id);
    AppointmentDTO create(Integer historyId, AppointmentDTO appointment);
    AppointmentDTO update(Integer id, AppointmentDTO appointment);
    void delete(Integer id);
}
