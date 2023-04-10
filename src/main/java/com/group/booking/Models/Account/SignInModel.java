package com.group.booking.Models.Account;

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
public class SignInModel {
    
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.USERNAME_VALIDATE)
    @NotEmpty(message = Message.USERNAME_NOT_EMP)
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = Message.PASSWORD_VALIDATE)
    @NotEmpty(message = Message.PASSWORD_NOT_EMP)
    private String password;

}
