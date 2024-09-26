package com.hampcode.service;

import com.hampcode.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> findAll();
    DoctorDTO getOne(Integer id);
    DoctorDTO create(DoctorDTO doctor);
    DoctorDTO update(Integer id, DoctorDTO doctor);
    void delete(Integer id);
}
