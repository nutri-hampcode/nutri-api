package com.hampcode.repository;

import com.hampcode.model.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Integer>{
}
