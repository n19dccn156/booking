package com.group.booking.Controllers.Account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Account.RoleImpl;
import com.group.booking.Models.Account.RoleModel;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Services.Account.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/roles")
@Api(tags = "Role")
public class RoleController implements RoleImpl {
    
    @Autowired RoleService roleService;

    @Override
    @GetMapping("")
    @ApiOperation(value = "Get all role [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getAllRole() {
        List<RoleModel> foundListRole = roleService.getAllRole();
        return !foundListRole.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundListRole)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, foundListRole)
            );
    }

    @Override
    @PutMapping("/{roleId}/{name}")
    @ApiOperation(value = "Update role name By Id [ADMIN, EMPLOYEE]", consumes = "application/json")
    public ResponseEntity<ResponseObject> updateRole(@PathVariable("roleId") String roleId, @PathVariable("name") String name) {
        RoleModel role = roleService.updateRole(roleId, name);
        return role != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, role)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, "")
            );
    }

    
    
}
