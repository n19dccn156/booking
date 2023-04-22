package com.group.booking.Controllers.Hotel;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @GetMapping("/hotel")
    @ApiOperation(value = "Get Room type by authorization  [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getRoomTypeByHotelId(HttpServletRequest request) {
        Set<RoomTypeModel> foundRooms = roomTypeService.findByAuthorization(request.getHeader("Authorization"));
        return foundRooms != null && !foundRooms.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundRooms)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @PatchMapping("")
    @ApiOperation(value = "Update Room type by authorization  [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> putRoomTypeById(@RequestBody @Validated RoomTypeModel rooom, HttpServletRequest request) {
        RoomTypeModel foundRoom = roomTypeService.updateById(rooom, request.getHeader("Authorization"));
        return foundRoom != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, foundRoom)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, "")
            );
    }

    @Override
    @PostMapping("")
    @ApiOperation(value = "Save Room type by authorization  [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> postRoomType(@RequestBody @Validated RoomTypeModel roomTypeModel, HttpServletRequest request) {
        RoomTypeModel room = roomTypeService.postRoomType(roomTypeModel, request.getHeader("Authorization"));
        return room != null ?
            ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.INSERT_SUCCESS, room)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.INSERT_FAILED, "")
            );
    }
    
}
