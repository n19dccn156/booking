package com.group.booking.Models.Account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    // @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.USERNAME_VALIDATE)
    @NotEmpty(message = Message.USERNAME_NOT_EMP)
    private String username;

    
    // @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.PASSWORD_VALIDATE)
    @NotEmpty(message = Message.PASSWORD_NOT_EMP)
    private String password;
    
    // @Pattern(regexp = "^[a-zA-Z]{1, 50}$", message = Message.FIRSTNAME_VALIDATE)
    @NotEmpty(message = Message.FIRSTNAME_NOT_EMP)
    private String firstname;

    // @Pattern(regexp = "^[a-zA-Z]{1, 50}$", message = Message.LASTNAME_VALIDATE)
    @NotEmpty(message = Message.LASTNAME_NOT_EMP)
    private String lastname;

    // @NotEmpty(message = Message.BIRTHDAY_NOT_EMP)
    private Date birthday;

    @Column(unique = true)
    @Email(message = Message.EMAIL_VALIDATE)
    @NotEmpty(message = Message.EMAIL_NOT_EMP)
    private String email;

    @Column(unique = true)
    // @Pattern(regexp = "[0-9]{10}$", message = Message.PHONE_VALIDATE)
    @NotEmpty(message = Message.PHONE_NOT_EMP)
    private String phone;

    // @Pattern(regexp = "^[a-zA-Z]{3, 5}$", message = Message.GENDER_VALIDATE)
    @NotEmpty(message = Message.GENDER_NOT_EMP)
    private String gender;
    
    // @Temporal(TemporalType.DATE)
    private String avatar;

    private String verify;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "role_id")
    private String roleId;

    public UserModel(SignUpModel model) {
        this.username = model.getUsername();
        this.password = model.getPassword();
        this.firstname = model.getFirstname();
        this.lastname = model.getLastname();
        this.email = model.getEmail();
        this.phone = model.getPhone();
        this.birthday = model.getBirthday();
        this.gender = model.getGender();
        this.avatar = Const.HOST+"/api/v1/images/1";
    }
}
