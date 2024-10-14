package com.hampcode.service.impl;

import com.hampcode.dto.NutritionalPlanDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.model.entity.Doctor;
import com.hampcode.model.entity.NutritionalPlan;
import com.hampcode.model.entity.User;
import com.hampcode.repository.DoctorRepository;
import com.hampcode.repository.NutritionalPlanRepository;
import com.hampcode.repository.UserRepository;
import com.hampcode.service.NutritionalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionalPlanServiceImpl implements NutritionalPlanService {

    @Autowired
    private NutritionalPlanRepository nutritionalPlanRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public NutritionalPlan createNutritionalPlan(NutritionalPlanDTO nutritionalPlanDTO) {
        NutritionalPlan nutritionalPlan = convertToEntity(nutritionalPlanDTO);
        return nutritionalPlanRepository.save(nutritionalPlan);
    }

    @Override
    public List<NutritionalPlan> findAllPlans() {
        return nutritionalPlanRepository.findAll();
    }

    @Override
    public NutritionalPlan findPlanById(Integer id) {
        return nutritionalPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado con ID: " + id));
    }

    @Override
    public NutritionalPlan updatePlan(Integer id, NutritionalPlanDTO nutritionalPlanDTO) {
        NutritionalPlan existingPlan = findPlanById(id);
        existingPlan.setType(nutritionalPlanDTO.getType());

        Doctor doctor = doctorRepository.findById(nutritionalPlanDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado con ID: " + nutritionalPlanDTO.getDoctorId()));
        existingPlan.setDoctor(doctor);

        User user = userRepository.findById(nutritionalPlanDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + nutritionalPlanDTO.getUserId()));
        existingPlan.setUser(user);

        return nutritionalPlanRepository.save(existingPlan);
    }

    @Override
    public void deletePlan(Integer id) {
        NutritionalPlan existingPlan = findPlanById(id);
        nutritionalPlanRepository.delete(existingPlan);
    }

    @Override
    public NutritionalPlan convertToEntity(NutritionalPlanDTO nutritionalPlanDTO) {
        NutritionalPlan nutritionalPlan = new NutritionalPlan();
        nutritionalPlan.setType(nutritionalPlanDTO.getType());

        Doctor doctor = doctorRepository.findById(nutritionalPlanDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado con ID: " + nutritionalPlanDTO.getDoctorId()));
        nutritionalPlan.setDoctor(doctor);

        User user = userRepository.findById(nutritionalPlanDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + nutritionalPlanDTO.getUserId()));
        nutritionalPlan.setUser(user);

        return nutritionalPlan;
    }
}
