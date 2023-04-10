package com.group.booking.Interfaces.Hotel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.ProvincePostModel;

public interface ProvinceImpl {
    
    public ResponseEntity<ResponseObject> getAllProvinces();
    public ResponseEntity<ResponseObject> getById(String id);
    public ResponseEntity<ResponseObject> putProvince(ProvincePostModel province, HttpServletRequest request);
    public ResponseEntity<ResponseObject> postProvince(ProvincePostModel province, HttpServletRequest request);
}
