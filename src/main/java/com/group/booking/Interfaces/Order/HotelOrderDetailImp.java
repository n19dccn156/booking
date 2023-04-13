package com.group.booking.Interfaces.Order;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;

public interface HotelOrderDetailImp {
    
    public ResponseEntity<ResponseObject> getOrderByOrderId(int userId);
}
