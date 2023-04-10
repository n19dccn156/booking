package com.group.booking.Controllers.Hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Hotel.ProvinceImpl;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.ProvinceModel;
import com.group.booking.Models.Hotel.ProvincePostModel;
import com.group.booking.Services.Hotel.ProvinceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/provinces")
@Api(tags = "Province")
public class ProvinceController implements ProvinceImpl {


    @Autowired
    private ProvinceService provinceService;

    @Override
    @GetMapping("")
    @ApiOperation(value = "Get all Provinces [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getAllProvinces() {
        List<ProvinceModel> provinces = provinceService.getAllProvinces();
        return !provinces.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, provinces)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Get province by id [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getById(@PathVariable("id") String id) {
        ProvinceModel province = provinceService.getById(id);
        return province != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, province)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "put province [ADMIN, EMPLOYEE]", consumes = "application/json")
    public ResponseEntity<ResponseObject> putProvince(ProvincePostModel province, HttpServletRequest request) {
        ProvinceModel foundProvince = provinceService.putProvince(province, request.getHeader("Authorization"));
        if(foundProvince != null && foundProvince.getId().length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject(Const.STATUS_FAILED, foundProvince.getName(), "")
            );
        }
        return foundProvince != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, foundProvince)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, "")
            );
    }

    @Override
    @PostMapping("")
    @ApiOperation(value = "Post province [ADMIN, EMPLOYEE]", consumes = "application/json")
    public ResponseEntity<ResponseObject> postProvince(ProvincePostModel province, HttpServletRequest request) {   
        ProvinceModel foundProvince = provinceService.postProvince(province, request.getHeader("Authorization"));
        if(foundProvince != null && foundProvince.getId().length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject(Const.STATUS_FAILED, foundProvince.getName(), "")
            );
        }
        return foundProvince != null ?
            ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.INSERT_SUCCESS, foundProvince)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.INSERT_FAILED, "")
            );
    }

    
}
