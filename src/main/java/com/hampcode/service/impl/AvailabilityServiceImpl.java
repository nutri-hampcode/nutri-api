package com.hampcode.service.impl;

import com.hampcode.dto.AvailabilityCreateUpdateDTO;
import com.hampcode.dto.AvailabilityDetailsDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.AvailabilityMapper;
import com.hampcode.model.entity.Availability;
import com.hampcode.model.entity.Doctor;
import com.hampcode.repository.AvailabilityRepository;
import com.hampcode.repository.DoctorRepository;
import com.hampcode.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;
    private final AvailabilityMapper availabilityMapper;

    @Transactional(readOnly = true)
    @Override
    public List<AvailabilityDetailsDTO> findAll() {
        List<Availability> avs = availabilityRepository.findAll();
        return avs.stream()
                .map(availabilityMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AvailabilityDetailsDTO> findDoctorAvailability(Integer id_doctor){
        List<Availability> avs = availabilityRepository.findByDoctor(id_doctor)
                .orElseThrow(()-> new ResourceNotFoundException("Availabilities not found for Doctor with id: "+id_doctor));
        return avs.stream()
                .map(availabilityMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public AvailabilityDetailsDTO findById(Integer id) {
        Availability av = availabilityRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Availability not found with id: " + id));
        return availabilityMapper.toDetailsDTO(av);
    }

    @Transactional
    @Override
    public AvailabilityDetailsDTO create(Integer id_doctor, AvailabilityCreateUpdateDTO availabilityCreateDTO) {
        Doctor doc = doctorRepository.findById(id_doctor).orElseThrow(()-> new ResourceNotFoundException("Doctor not found with id: " + id_doctor));
        Availability av = availabilityMapper.toEntity(availabilityCreateDTO);
        av.setDoctor(doc);
        return availabilityMapper.toDetailsDTO(availabilityRepository.save(av));
    }

    @Transactional
    @Override
    public AvailabilityDetailsDTO update(Integer id, AvailabilityCreateUpdateDTO availabilityUpdateDTO) {
        Availability aux = availabilityRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Availability not found with id: " + id));

        aux.setTime(availabilityUpdateDTO.getTime());
        aux.setDate(availabilityUpdateDTO.getDate());
        aux.setReserved(availabilityUpdateDTO.getReserved());
        return availabilityMapper.toDetailsDTO(availabilityRepository.save(aux));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Availability av = availabilityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Availability not found with id: " + id));
        availabilityRepository.delete(av);
    }
}