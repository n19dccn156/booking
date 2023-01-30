package com.be.booking.Services.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.be.booking.Models.Adds.ResponseObject;
import com.be.booking.Repositories.Hotel.HotelRepository;
import com.be.booking.Repositories.OrderHotel.OrderHotelRepository;

@Service
public class HotelService {
    
    @Autowired HotelRepository hotelRepository;
    @Autowired OrderHotelRepository orderHotelRepository;

    public ResponseEntity<ResponseObject> find10HotelsRecentlyBooked() throws Exception{

        return null;
    }
}