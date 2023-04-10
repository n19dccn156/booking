package com.group.booking.Interfaces.Account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Account.ChangePasswordModel;
import com.group.booking.Models.Account.SignInModel;
import com.group.booking.Models.Account.SignUpModel;
import com.group.booking.Models.Addons.ResponseObject;

public interface UserImpl {
    
    public ResponseEntity<ResponseObject> signup(SignUpModel model);
    public ResponseEntity<ResponseObject> signing(SignInModel model);
    public ResponseEntity<ResponseObject> forgot(String email);
    public ResponseEntity<ResponseObject> reset(int userId);
    public ResponseEntity<ResponseObject> changePassword(ChangePasswordModel model);
    public ResponseEntity<ResponseObject> getByAuthorization(int userId, HttpServletRequest request);


}
