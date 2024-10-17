package com.hampcode.service;

import com.hampcode.model.entity.Membership;

import java.util.List;

public interface MembershipService {
    List<Membership> findAll();
    Membership findById(Integer id);
    Membership create(Membership membership);
    Membership update(Integer id, Membership membership);
    void delete(Integer id);
}