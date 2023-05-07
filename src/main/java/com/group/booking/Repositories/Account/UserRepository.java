package com.group.booking.Repositories.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group.booking.Models.Account.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    
    public Optional<UserModel> findById(int id);
    public Optional<UserModel> findByUsername(String username);
    public Optional<UserModel> findByEmail(String email);
    public List<UserModel> findByUsernameOrEmailOrPhone(String username, String email, String phone);
    public Optional<UserModel> findByVerify(String token);
    @Transactional
    @Modifying
    @Query(value = ""+
        "INSERT INTO "+
        "USERS (username, password, firstname, lastname, "+
                "phone, email, birthday, gender, "+
                "avatar, is_active, role_id) "+
        "VALUES(username = ?1, "+
            "password = ?2, "+
            "firstname = ?3, "+
            "lastname = ?4, "+
            "phone = ?5, "+
            "email = ?6, "+
            "birthday = ?7, "+
            "gender = ?8, "+
            "avatar = ?9, "+
            "is_active = ?10, "+
            "role_id = ?11)", nativeQuery = true)
    public UserModel addUser(String username, String password, String firstname, String lastname,
                        String phone, String email, String birthday, String gender,
                        String avatar, boolean isActive, String roleId);
}
