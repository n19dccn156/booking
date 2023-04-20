package com.group.booking.Models.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {

    @NotEmpty(message = Message.FIRSTNAME_NOT_EMP)
    private String firstname;

    @NotEmpty(message = Message.LASTNAME_NOT_EMP)
    private String lastname;

    @Email(message = Message.EMAIL_VALIDATE)
    @NotEmpty(message = Message.EMAIL_NOT_EMP)
    private String email;

    @Pattern(regexp = "[0-9]{10}$", message = Message.PHONE_VALIDATE)
    @NotEmpty(message = Message.PHONE_NOT_EMP)
    private String phone;

    @NotEmpty(message = Message.BIRTHDAY_NOT_EMP)
    private String birthday;

    @NotEmpty(message = Message.GENDER_NOT_EMP)
    private String gender;
    
}
