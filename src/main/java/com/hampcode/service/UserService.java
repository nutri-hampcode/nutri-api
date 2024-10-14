package com.hampcode.service;

import com.hampcode.dto.UserCUDTO;

import java.util.List;

public interface UserService {
    List<UserCUDTO> findAll();
    UserCUDTO getOne(Integer id);
    UserCUDTO create(UserCUDTO user);
    UserCUDTO update(Integer id, UserCUDTO user);
    UserCUDTO findByUsername(String username);
    UserCUDTO findByEmail(String email);
    void delete(Integer id);
    boolean checkCredentials(String username, String password);
    void recoverAccount(String email, String password);
}