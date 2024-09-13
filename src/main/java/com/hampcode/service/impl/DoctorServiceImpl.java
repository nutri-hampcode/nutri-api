package com.hampcode.service.impl;

import com.hampcode.model.entity.Doctor;
import com.hampcode.repository.DoctorRepository;
import com.hampcode.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Doctor getOne(Integer id) {
        return doctorRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    @Transactional
    @Override
    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Transactional
    @Override
    public Doctor update(Integer id, Doctor doctor) {
        Doctor doc = doctorRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        doc.setFirst_name(doctor.getFirst_name());
        doc.setLast_name(doctor.getLast_name());
        return doctorRepository.save(doc);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        doctorRepository.deleteById(id);
    }
}