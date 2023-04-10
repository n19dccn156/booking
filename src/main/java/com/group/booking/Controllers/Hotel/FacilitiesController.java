package com.group.booking.Controllers.Hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Hotel.FacilitiesImpl;
import com.group.booking.Models.Addons.FacilitiesHotelResponse;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Services.Hotel.FacilitiesHotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/facilities")
@Api(tags = "Facilities")
public class FacilitiesController implements FacilitiesImpl {
    
    @Autowired
    private FacilitiesHotelService facilitiesHotelService;

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

}
