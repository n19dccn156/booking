package com.group.booking.Repositories.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Account.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, String> {
    
}
