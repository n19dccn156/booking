package com.group.booking.Interfaces.Hotel;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;

public interface HotelTypeImpl {
    
    public ResponseEntity<ResponseObject> getAllTypeHotel();
}
