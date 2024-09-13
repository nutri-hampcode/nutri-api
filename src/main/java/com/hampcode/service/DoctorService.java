package com.hampcode.service;

import com.hampcode.model.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();
    Doctor getOne(Integer id);
    Doctor create(Doctor doctor);
    Doctor update(Integer id, Doctor doctor);
    void delete(Integer id);
}
