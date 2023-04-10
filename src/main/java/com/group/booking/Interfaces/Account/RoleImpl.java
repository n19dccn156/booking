package com.group.booking.Interfaces.Account;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;

public interface RoleImpl {
    
    public ResponseEntity<ResponseObject> getAllRole();
    public ResponseEntity<ResponseObject> updateRole(String roleId, String name);

}
