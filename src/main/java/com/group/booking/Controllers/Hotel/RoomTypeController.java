package com.group.booking.Controllers.Hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Hotel.RoomTypeImpl;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.RoomTypeModel;
import com.group.booking.Services.Hotel.RoomTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/room-type")
@Api(tags = "Room Type")
public class RoomTypeController implements RoomTypeImpl {

    @Autowired
    private RoomTypeService roomTypeService;

    @Override
    @GetMapping("/hotel/{hotelId}")
    @ApiOperation(value = "Get Room type by hotelId, checkin, checkout  [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getRoomTypeByHotelId(
        @PathVariable("hotelId") int hotelId, 
        @RequestParam(name = "checkin", required = true) String checkin, 
        @RequestParam(name = "checkout", required = true) String checkout) {

        List<RoomTypeModel> foundRoomType = roomTypeService.findByHotelId(hotelId, checkin, checkout);
        return foundRoomType != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundRoomType)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    public ResponseEntity<ResponseObject> putRoomTypeById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putRoomTypeById'");
    }

    @Override
    public ResponseEntity<ResponseObject> postRoomType(RoomTypeModel roomTypeModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postRoomType'");
    }
    
}
