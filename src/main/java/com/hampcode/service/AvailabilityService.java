package com.hampcode.service;

import com.hampcode.model.entity.Availability;

import java.util.List;

public interface AvailabilityService {
    List<Availability> findAll();
    Availability findById(Integer id);
    Availability create(Integer id_doctor, Availability availability);
    Availability update(Integer id, Availability availability);
    void delete(Integer id);
}
