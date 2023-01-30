package com.be.booking.Controllers.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.be.booking.Models.Adds.ResponseObject;
import com.be.booking.Repositories.Account.RoleRepository;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired 
    private RoleRepository roleRepository;
    
    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("success", "Tìm thành công", roleRepository.findAll())
        );
    }
}
