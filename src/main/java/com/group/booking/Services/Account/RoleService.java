package com.group.booking.Services.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Models.Account.RoleModel;
import com.group.booking.Repositories.Account.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    public RoleModel foundById(String Id) {
        try {
            Optional<RoleModel> foundRole = roleRepository.findById(Id);
            return foundRole.isPresent() ? foundRole.get() : null;   
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RoleModel> getAllRole() {
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RoleModel updateRole(String id, String name) {
        try {
            RoleModel foundRole = foundById(id);
            if(foundRole != null) {
                foundRole.setName(name);
                roleRepository.save(foundRole);
                return foundRole;
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
