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
    @Override
    public UserMembershipDetailsDTO create(Integer userId, UserMembershipCreateUpdateDTO userMembershipDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        //si el usuario cuenta con membresia no se puede registrar otra
        if(userMembershipRepository.existsByUserId(userId)){
            throw new ResourceNotFoundException("User already has a membership");
        }
        UserMembership userMembership = userMembershipMapper.toUserMembership(userMembershipDto);
        userMembership.setUser(user);
        //setEnd_date
        userMembership.setEnd_date(userMembership.getStart_date().plusMonths(1));

        userMembership.setStatus(true);

        UserMembership savedUserMembership = userMembershipRepository.save(userMembership);
        return UserMembershipMapper.toDetailsDTO(savedUserMembership);
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

        userMembership.setStatus(false);
        UserMembership savedUserMembership = userMembershipRepository.save(userMembership);
        return UserMembershipMapper.toDetailsDTO(savedUserMembership);
    }
}