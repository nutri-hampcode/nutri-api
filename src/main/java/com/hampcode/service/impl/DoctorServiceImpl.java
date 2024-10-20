package com.hampcode.service.impl;

import com.hampcode.dto.DoctorDTO;
import com.hampcode.exception.BadRequestException;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.DoctorMapper;
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
    private final DoctorMapper doctorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<DoctorDTO> findAll() {
        List<Doctor>doctors = doctorRepository.findAll();
        return doctors.stream().
                map(doctorMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public DoctorDTO getOne(Integer id) {
        Doctor doctor = doctorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return doctorMapper.toDTO(doctor);
    }

    @Transactional
    @Override
    public DoctorDTO create(DoctorDTO doctorDTO) {
        doctorRepository.findByFirstNameAndLastName(doctorDTO.getFirstName(), doctorDTO.getLastName())
                .ifPresent(existingDoctor -> {
                    throw new BadRequestException("Doctor already exists!");
                });
        Doctor doctor = doctorMapper.toEntity(doctorDTO);
        doctorRepository.save(doctor);
        return doctorMapper.toDTO(doctor);
    }

    @Transactional
    @Override
    public DoctorDTO update(Integer id, DoctorDTO doctorDTO) {
        Doctor docFromDb = doctorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));

        doctorRepository.findByFirstNameAndLastName(doctorDTO.getFirstName(), doctorDTO.getLastName())
                        .filter(existingDoctor -> !existingDoctor.getId().equals(id))
                        .ifPresent(existingDoctor -> {throw new BadRequestException("Doctor already exists!");});

        docFromDb.setFirstName(doctorDTO.getFirstName());
        docFromDb.setLastName(doctorDTO.getLastName());

        doctorRepository.save(docFromDb);

        return doctorMapper.toDTO(docFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.delete(doctor);
    }
}