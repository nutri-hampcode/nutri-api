package com.hampcode.service.impl;

import com.hampcode.dto.UserMembershipCreateUpdateDTO;
import com.hampcode.dto.UserMembershipDetailsDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.mapper.UserMembershipMapper;
import com.hampcode.model.entity.UserMembership;
import com.hampcode.model.entity.User;
import com.hampcode.repository.UserMembershipRepository;
import com.hampcode.repository.UserRepository;
import com.hampcode.service.UserMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserMembershipServiceImpl implements UserMembershipService {

    private final UserMembershipRepository userMembershipRepository;
    private final UserRepository userRepository;
    private final UserMembershipMapper userMembershipMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserMembershipDetailsDTO> findAll() {
        return userMembershipRepository.findAll().stream()
                .map(UserMembershipMapper::toDetailsDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserMembershipDetailsDTO findById(Integer userId) {
        UserMembership userMembership = userMembershipRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserMembership not found for User with id: " + userId));
        return UserMembershipMapper.toDetailsDTO(userMembership);
    }

    @Transactional
    public UserMembershipDetailsDTO confirmMembership(Integer userId) {
        UserMembership userMembership = userMembershipRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserMembership not found for User with id: " + userId));

        // Actualiza la membresía con los detalles necesarios
        userMembership.setEnd_date(userMembership.getStart_date().plusMonths(1));
        userMembership.setStatus(true);
        // Guarda los cambios en el repositorio
        UserMembership savedUserMembership = userMembershipRepository.save(userMembership);

        // Devuelve los detalles de la membresía actualizada
        return UserMembershipMapper.toDetailsDTO(savedUserMembership);
    }

    @Transactional
    public UserMembershipDetailsDTO create(Integer userId, UserMembershipCreateUpdateDTO userMembershipDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Si el usuario ya tiene una membresía, no se puede registrar otra
        if (userMembershipRepository.existsByUserId(userId)) {
            throw new ResourceNotFoundException("User already has a membership");
        }

        // Crear la nueva membresía
        UserMembership userMembership = userMembershipMapper.toUserMembership(userMembershipDto);
        userMembership.setUser(user);
        userMembershipRepository.save(userMembership);

        return UserMembershipMapper.toDetailsDTO(userMembership);
    }


    @Transactional
    @Override
    public UserMembershipDetailsDTO update(Integer userId, UserMembershipCreateUpdateDTO userMembershipDto) {
        UserMembership existingUserMembership = userMembershipRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserMembership not found for User with id: " + userId));

        UserMembership updatedUserMembership = userMembershipMapper.toUserMembership(userMembershipDto);
        updatedUserMembership.setId(existingUserMembership.getId());
        updatedUserMembership.setUser(existingUserMembership.getUser());
        updatedUserMembership.setEnd_date(updatedUserMembership.getStart_date().plusMonths(1));

        updatedUserMembership.setStatus(true);

        UserMembership savedUserMembership = userMembershipRepository.save(updatedUserMembership);
        return UserMembershipMapper.toDetailsDTO(savedUserMembership);
    }

    @Transactional
    @Override
    public UserMembershipDetailsDTO delete(Integer userId) {
        UserMembership userMembership = userMembershipRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserMembership not found for User with id: " + userId));

        // Eliminar completamente la membresía
        userMembershipRepository.delete(userMembership);
        userMembershipRepository.flush();
        UserMembership savedUserMembership = userMembershipRepository.save(userMembership);
        return UserMembershipMapper.toDetailsDTO(savedUserMembership);
    }
}