package com.hampcode.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.hampcode.exception.BadRequestException;
import com.hampcode.repository.RoleRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hampcode.dto.UserCUDTO;
import com.hampcode.dto.UserLoginDTO;
import com.hampcode.dto.AuthResponseDTO;
import com.hampcode.dto.UserRegistrationDTO;
import com.hampcode.exception.ResourceNotFoundException;
import com.hampcode.exception.RoleNotFoundException;
import com.hampcode.mapper.UserMapper;
import com.hampcode.model.entity.User;
import com.hampcode.model.entity.Customer;
import com.hampcode.model.entity.Role;
import com.hampcode.security.TokenProvider;
import com.hampcode.security.UserPrincipal;
import com.hampcode.repository.RoleRepository;
import com.hampcode.model.enums.ERole;
import com.hampcode.repository.DietTypeRepository;
import com.hampcode.repository.GoalRepository;
import com.hampcode.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hampcode.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GoalRepository goalRepository;
    private final DietTypeRepository dietTypeRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;


    @Transactional(readOnly = true)
    @Override
    public List<UserCUDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserCUDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserCUDTO getOne(Integer id) {
        User user= userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toUserCUDTO(user);
    }

    @Transactional
    @Override
    public UserCUDTO create(UserRegistrationDTO userRegistrationDTO) {
        userRepository.findByEmail(userRegistrationDTO.getEmail())
                .ifPresent(existingUser -> {
                    throw new BadRequestException("Error: Email is already registered.");
                });
        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        User user= userMapper.toUserEntity(userRegistrationDTO);
        Role role = roleRepository.findByName(ERole.CUSTOMER)
                .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
        user.setRole(role);
        Customer customer = new Customer();
        customer.setName(userRegistrationDTO.getName());
        customer.setUsername(userRegistrationDTO.getUsername());
        customer.setUser(user);
        user.setCustomer(customer);

        User saveuser= userRepository.save(user);
        return userMapper.toUserCUDTO(saveuser);
    }

    @Transactional
    @Override
    public UserCUDTO update(Integer id, UserCUDTO user) {
        User us = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        us.getCustomer().setName(user.getName());
        us.getCustomer().setUsername(user.getUsername());
        us.setEmail(user.getEmail());
        us.getCustomer().setHeight(user.getHeight());
        us.getCustomer().setWeight(user.getWeight());
        us.getCustomer().setAge(user.getAge());
        us.getCustomer().setAllergies(user.getAllergies());
        us.getCustomer().setGoal(user.getGoalId() != null ? goalRepository.findById(user.getGoalId()).orElse(null) : null);
        us.getCustomer().setDietType(user.getDietTypeId() != null ? dietTypeRepository.findById(user.getDietTypeId()).orElse(null) : null);
        User updateUser = userRepository.save(us);
        return userMapper.toUserCUDTO(updateUser);
    }

    @Transactional
    @Override
    public UserCUDTO findByEmail(String email){
        User user= userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toUserCUDTO(user);
    }



    @Transactional
    @Override
    public void recoverAccount(String email,String newPassword) {
        User us = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        us.setPassword(newPassword);
        userRepository.save(us);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public AuthResponseDTO login(UserLoginDTO userLoginDTO){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        String token = tokenProvider.createAccessToken(authentication);
        AuthResponseDTO responseDTO = userMapper.toAuthResponseDTO(user,token);

        return responseDTO;
    }
}