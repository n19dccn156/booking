package com.group.booking.Models.Account;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    
    private int id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String email;
    private String phone;
    private String gender;
    private String avatar;
    private String roleId;
    private String roleName;

    public UserResponse(UserModel user, String roleName) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.birthday = user.getBirthday();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.avatar = user.getAvatar();
        this.roleId = user.getRoleId();
        this.roleName = roleName;

    }
}
