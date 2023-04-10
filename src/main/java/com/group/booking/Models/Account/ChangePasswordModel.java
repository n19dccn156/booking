package com.group.booking.Models.Account;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordModel {
    
    private int userId;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.PASSWORD_VALIDATE)
    @NotEmpty(message = Message.PASSWORD_NOT_EMP)
    private String oldPassword;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.PASSWORD_VALIDATE)
    @NotEmpty(message = Message.PASSWORD_NOT_EMP)
    private String newPassword;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.PASSWORD_VALIDATE)
    @NotEmpty(message = Message.PASSWORD_NOT_EMP)
    private String newPassword2;

}