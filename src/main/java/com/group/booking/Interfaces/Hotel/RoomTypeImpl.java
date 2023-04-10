package com.group.booking.Interfaces.Hotel;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.RoomTypeModel;

public interface RoomTypeImpl {
    
    public ResponseEntity<ResponseObject> getRoomTypeByHotelId(int hotelId, String checkin, String checkout);
    public ResponseEntity<ResponseObject> putRoomTypeById(int id);
    public ResponseEntity<ResponseObject> postRoomType(RoomTypeModel roomTypeModel);

}
