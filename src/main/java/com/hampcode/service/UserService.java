package com.hampcode.service;

import com.hampcode.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User getOne(Integer id);
    User create(User user);
    User update(Integer id, User user);
    User findByUsername(String username);
    User findByEmail(String email);
    void delete(Integer id);
    boolean checkCredentials(String username, String password);
    void recoverAccount(String email, String password);
}