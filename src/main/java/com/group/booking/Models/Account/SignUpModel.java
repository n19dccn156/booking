package com.group.booking.Models.Account;

import java.sql.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpModel {
    
    @Column(unique = true, nullable = false)
    // @UniqueElements(message = Message.USERNAME_UNIQUE)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.USERNAME_VALIDATE)
    @NotEmpty(message = Message.USERNAME_NOT_EMP)
    private String username;
    
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.PASSWORD_VALIDATE)
    @NotEmpty(message = Message.PASSWORD_NOT_EMP)
    private String password;
    
    @Pattern(regexp = "^[a-zA-Z]{1,50}$", message = Message.FIRSTNAME_VALIDATE)
    @NotEmpty(message = Message.FIRSTNAME_NOT_EMP)
    private String firstname;

    @Pattern(regexp = "^[a-zA-Z]{1,50}$", message = Message.LASTNAME_VALIDATE)
    @NotEmpty(message = Message.LASTNAME_NOT_EMP)
    private String lastname;

    private Date birthday;

    @Column(unique = true)
    @Email(message = Message.EMAIL_VALIDATE)
    @NotEmpty(message = Message.EMAIL_NOT_EMP)
    private String email;

    @Column(unique = true)
    // @UniqueElements(message = Message.PHONE_UNIQUE)
    @Pattern(regexp = "(^$|[0-9]{10,10}$)", message = Message.PHONE_VALIDATE)
    @NotEmpty(message = Message.PHONE_NOT_EMP)
    private String phone;

    @Pattern(regexp = "(^$|[a-zA-Z]{2,4}$)", message = Message.GENDER_VALIDATE)
    @NotEmpty(message = Message.GENDER_NOT_EMP)
    private String gender;

    public void preProcessing() {
        this.username = username.toLowerCase().trim();
        this.password = password.trim();
        this.email = email.toLowerCase().trim();
        this.firstname = firstname.trim();
        this.lastname = lastname.trim();
        this.gender = gender.trim();
    }
}
