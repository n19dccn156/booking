package com.be.booking.Models.Account;


import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserModel {
    
    @Id
    private ObjectId _id;
    @NotBlank(message = "Tài khoản không được có khoảng trống")
    @Size(min = 5, max = 20, message = "Tài khoản từ 5 - 20 ký tự")
    private String username;
    @NotBlank(message = "Mật khẩu không được có khoảng trống")
    @Size(min = 5, max = 20, message = "Mật khẩu từ 5 - 20 ký tự")
    private String password;
    @NotEmpty(message = "Họ không được bỏ trống")
    private String firstname;
    @NotEmpty(message = "Tên không được bỏ trống")
    private String lastname;
    private String birthday;
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotBlank(message = "Số điện thoại không được bỏ trống")
    @Size(min = 10, max = 10, message = "Số điện thoại phải 10 ký tự")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Số điện thoại không hợp lệ")
    private String phone;
    @NotEmpty(message = "Giới tính không được bỏ trống")
    private String gender;
    private String avatar;
    private String accessToken;
    private String verify;
    private List<String> roles;

}
