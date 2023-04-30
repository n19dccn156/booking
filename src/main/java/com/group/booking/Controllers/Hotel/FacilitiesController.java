package com.group.booking.Controllers.Hotel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Hotel.FacilitiesImpl;
import com.group.booking.Models.Addons.FacilitiesHotelResponse;
import com.group.booking.Models.Addons.ListObject;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.FacilitiesModel;
import com.group.booking.Services.Hotel.FacilitiesHotelService;
import com.group.booking.Services.Hotel.FacilitiesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/facilities")
@Api(tags = "Facilities")
public class FacilitiesController implements FacilitiesImpl {
    
    @Autowired
    private FacilitiesHotelService facilitiesHotelService;
    @Autowired
    private FacilitiesService facilitiesService;

    @Override
    @GetMapping("/hotel/{hotelId}")
    @ApiOperation(value = "Get by hotel_id [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> findByHotelId(@PathVariable("hotelId") int hotelId) {  
        List<FacilitiesHotelResponse> data = facilitiesHotelService.findByHotelId(hotelId);
        return data != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, data)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("/hotel/find/{hotelId}")
    @ApiOperation(value = "Get by hotel_id [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> findAllForInsert(@PathVariable("hotelId") int hotelId) {
        List<FacilitiesModel> data = facilitiesService.findByHotelIdToInsert(hotelId);
        return data != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, data)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("")
    @ApiOperation(value = "Get all [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> findAll() {
        List<FacilitiesModel> founds = facilitiesService.findAll();
        return founds != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, founds)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete by hotel [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> deleteForHotel(@PathVariable("id") int id, HttpServletRequest request) {
        boolean isDelete = facilitiesHotelService.deleteForHotel(id, request.getHeader("Authorization"));
        return isDelete ?
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.DELETE_SUCCESS, isDelete)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.DELETE_FAILED, isDelete)
            );

    }

    @Override
    public ResponseEntity<ResponseObject> deleteForManager(int id, HttpServletRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteForManager'");
    }

    @Override
    @PostMapping("")
    @ApiOperation(value = "Post facilities for hotel [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> saveForHotel(@RequestBody ListObject ids, HttpServletRequest request) {
        boolean isSave = facilitiesHotelService.saveForHotel(ids.getIds(), request.getHeader("Authorization"));
        return isSave ?
            ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.INSERT_SUCCESS, isSave)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.INSERT_FAILED, isSave)
            );
    }

    @Override
    public ResponseEntity<ResponseObject> saveForManager(FacilitiesModel facility, HttpServletRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveForManager'");
    }
    
}
