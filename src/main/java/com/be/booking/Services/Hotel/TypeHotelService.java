package com.be.booking.Services.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.be.booking.Models.Adds.ResponseObject;
import com.be.booking.Repositories.Hotel.TypeHotelRepository;

@Service
public class TypeHotelService {
    
    @Autowired TypeHotelRepository typeHotelRepository;

    /**
     * List type hotel
     * @return []
     * @throws Exception
     */
    public ResponseEntity<ResponseObject> findAllTypeHotel() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("success", "Truy vấn thành công", typeHotelRepository.findAll())
        );
    }

    
}
