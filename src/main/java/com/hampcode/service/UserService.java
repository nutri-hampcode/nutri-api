package com.hampcode.service;

import java.util.List;

import com.hampcode.dto.AuthResponseDTO;
import com.hampcode.dto.UserCUDTO;
import com.hampcode.dto.UserLoginDTO;
import com.hampcode.dto.UserRegistrationDTO;

public interface UserService {
    List<UserCUDTO> findAll();
    UserCUDTO getOne(Integer id);
    UserCUDTO create(UserRegistrationDTO user);
    UserCUDTO update(Integer id, UserCUDTO user);
    UserCUDTO findByEmail(String email);
    AuthResponseDTO login(UserLoginDTO userLoginDTO);
    void delete(Integer id);;
    void recoverAccount(String email, String password);
}