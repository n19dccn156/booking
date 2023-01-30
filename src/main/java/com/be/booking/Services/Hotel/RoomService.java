package com.be.booking.Services.Hotel;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.be.booking.Models.Adds.ResponseObject;
import com.be.booking.Repositories.Hotel.RoomRepository;

@Service
public class RoomService {
    
    @Autowired RoomRepository roomRepository;

    public ResponseEntity<ResponseObject> findRoomsByListRoomId(List<ObjectId> listRoomId) throws Exception {
        
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("success", "Truy vấn thành công", roomRepository.findAllByListRoomId(listRoomId))  
        );
    }
}
