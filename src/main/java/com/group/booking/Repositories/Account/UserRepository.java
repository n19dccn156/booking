package com.group.booking.Repositories.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Account.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    
    public Optional<UserModel> findByUsername(String username);
    public Optional<UserModel> findByEmail(String email);
    public List<UserModel> findByUsernameOrEmailOrPhone(String username, String email, String phone);
}
