package com.be.booking.Controllers.Hotel;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.be.booking.Models.Adds.ResponseObject;
import com.be.booking.Services.Hotel.RoomService;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    
    @Autowired RoomService roomService;

    @GetMapping("/listroom")
    public ResponseEntity<ResponseObject> findAllRoomByListId() {

        List<ObjectId> list = new ArrayList<>();
        list.add(new ObjectId("63d67168a730ca3f1dda82f3"));
        list.add(new ObjectId("63d67168a730ca3f1dda82f5"));
        try {
            return roomService.findRoomsByListRoomId(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không lấy được dữ liệu", "")
            );
        }
    }
}
