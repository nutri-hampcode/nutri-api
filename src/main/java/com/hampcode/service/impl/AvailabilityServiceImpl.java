package com.hampcode.service.impl;

import com.hampcode.model.entity.Availability;
import com.hampcode.model.entity.Doctor;
import com.hampcode.repository.AvailabilityRepository;
import com.hampcode.repository.DoctorRepository;
import com.hampcode.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Availability> findDoctorAvailability(Integer id_doctor){
        return availabilityRepository.findByDoctor(id_doctor)
                .orElseThrow(()-> new RuntimeException("Availabilities not found for Doctor with id: "+id_doctor));
    }

    @Transactional(readOnly = true)
    @Override
    public Availability findById(Integer id) {
        return availabilityRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Availability not found with id: " + id));
    }

    @Transactional
    @Override
    public Availability create(Integer id_doctor, Availability availability) {
        Doctor doc = doctorRepository.findById(id_doctor).orElseThrow(()-> new RuntimeException("Doctor not found with id: " + id_doctor));
        availability.setDoctor(doc);
        return availabilityRepository.save(availability);
    }

    @Transactional
    @Override
    public Availability update(Integer id, Availability availability) {
        Availability aux = availabilityRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Availability not found with id: " + id));
        aux.setTime(availability.getTime());
        aux.setDate(availability.getDate());
        aux.setReserved(availability.getReserved());
        return availabilityRepository.save(aux);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        availabilityRepository.deleteById(id);
    }
}