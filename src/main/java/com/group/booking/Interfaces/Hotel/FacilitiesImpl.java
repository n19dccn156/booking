package com.group.booking.Interfaces.Hotel;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;

public interface FacilitiesImpl {
    
    public ResponseEntity<ResponseObject> findByHotelId(int hotelId);
}
