package com.group.booking.Interfaces.Hotel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.RoomTypeModel;

public interface RoomTypeImpl {
    
    public ResponseEntity<ResponseObject> getRoomTypeByHotelId(int hotelId, String checkin, String checkout);
    public ResponseEntity<ResponseObject> putRoomTypeById(RoomTypeModel room, HttpServletRequest request);
    public ResponseEntity<ResponseObject> postRoomType(RoomTypeModel roomTypeModel, HttpServletRequest request);
    public ResponseEntity<ResponseObject> getRoomTypeByHotelId(HttpServletRequest request);

}
