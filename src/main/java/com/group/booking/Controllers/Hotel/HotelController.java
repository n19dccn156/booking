package com.group.booking.Controllers.Hotel;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.group.booking.Interfaces.Hotel.HotelImpl;
import com.group.booking.Models.Addons.HotelResponse;
import com.group.booking.Models.Addons.PrecentByRoomType;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Addons.ResultResponse;
import com.group.booking.Models.Addons.RevenueOn12MonthAgo;
import com.group.booking.Models.Hotel.HotelModel;
import com.group.booking.Models.Hotel.HotelPost;
import com.group.booking.Models.Hotel.HotelUpdate;
import com.group.booking.Models.Hotel.ImageHotelModel;
import com.group.booking.Services.Hotel.HotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/hotels")
@Api(tags = "Hotel")
public class HotelController implements HotelImpl {

    @Autowired
    private HotelService hotelService;

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Get a hotel [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getById(@PathVariable("id") int id) {
        HotelModel foundHotel = hotelService.foundById(id);
        return foundHotel != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundHotel)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("/")
    @ApiOperation(value = "Get all hotel param is page and size", consumes = "application/json")
    public ResponseEntity<ResponseObject> getAll(
            @RequestParam(name = "page", required = true) int page,
            @RequestParam(name = "size", required = true) int size ) {
        Page foundHotel = hotelService.foundAll(page, size);
        return foundHotel != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundHotel)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("/top8-rating")
    @ApiOperation(value = "Get top 8 hotel by rating [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getTop8ByRating() {
        List<HotelResponse> hotels = hotelService.findTop8ByRating();
        return !hotels.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, hotels)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("/top8-number-rating")
    @ApiOperation(value = "Get top 8 hotel by number rating [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getTop8ByNumberRating() {
        List<HotelResponse> hotels = hotelService.findTop8ByNumberRating();
        return !hotels.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, hotels)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("/top8-trending")
    @ApiOperation(value = "Get top 8 hotel by number order in 30day ago [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getTop8ByTrending() {
        
        return null;
    }

    @Override
    @GetMapping("")
    @ApiOperation(value = "Get hotels by filter [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getListHotelsByFilter(@RequestParam(required = true) HashMap<String, String> filter) {

        String message = hotelService.checkProvinceAndCheckinAndCheckout(filter);
        if(!message.equals("OK")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject(Const.STATUS_SUCCESS, message, "")
            );
        }
        ResultResponse data = hotelService.getListHotelsByFilter(filter);
        if(data.getData() != null && data.getData().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, data)
            );
        }
        return data != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, hotelService.getListHotelsByFilter(filter))
            )
            :
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                new ResponseObject(Const.STATUS_FAILED, "Service Unavailable", "")
            );
    }

    @Override
    @GetMapping("/{hotelId}/images")
    @ApiOperation(value = "Get images by hotel_id [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getImagesByHotelId(@PathVariable("hotelId") int hotelId) {
        List<ImageHotelModel> data = hotelService.findAllImageByHotelId(hotelId);
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
    @GetMapping("/{hotelId}/comments")
    @ApiOperation(value = "Get comments by hotel_id [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getCommentsByHotelId(
        @PathVariable("hotelId") int hotelId, 
        @RequestParam(name = "page", required = true) int page, 
        @RequestParam(name = "size", required = true) int size) {

        if(page < 1) page = 1;
        if(size < 1) size = 10;

        ResultResponse data = hotelService.findCommentByHotelId(hotelId, page, size);
        if(data.getData() != null && data.getData().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, data)
            );
        }
        
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
    @GetMapping("/revenue")
    @ApiOperation(value = "Get revenue by hotel_id [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> GetRevenueOn12MonthAgo(HttpServletRequest request) {
        List<RevenueOn12MonthAgo> data = hotelService.getRevenueOn12MonthAgo(request.getHeader("Authorization"));
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
    @GetMapping("/precent")
    @ApiOperation(value = "Get precent room-type by hotel_id [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getPercentByRoomType(HttpServletRequest request) {
        List<PrecentByRoomType> data = hotelService.getPrecentByRoomType(request.getHeader("Authorization"));
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
    @GetMapping("/authorization")
    @ApiOperation(value = "Get hotel by authorization [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getHotelByAuthorization(HttpServletRequest request) {
        HotelModel foundHotel = hotelService.foundByAuthorization(request.getHeader("Authorization"));
        return foundHotel != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundHotel)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @GetMapping("/description")
    @ApiOperation(value = "Get description by authorization [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getDescriptionByAuthorization(HttpServletRequest request) {
        String description = hotelService.getDescription(request.getHeader("Authorization"));
        return description != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, description)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }
    
    @Override
    @PatchMapping("/description")
    @ApiOperation(value = "Update description by authorization [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getDescriptionByAuthorization(@RequestParam(name = "description", required = true) String description, HttpServletRequest request) {
        String message = hotelService.updateDescription(description, request.getHeader("Authorization"));
        return message.equals("OK") ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, message)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, message, "")
            );
    }

    @Override
    @PatchMapping("")
    @ApiOperation(value = "Update hotel by authorization [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> updateHotel(@RequestBody @Validated HotelUpdate hotel, HttpServletRequest request) {
        HotelUpdate found = hotelService.updateHotel(hotel, request.getHeader("Authorization"));
        return found != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, found)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, "")
            );
    }

    @Override
    @PatchMapping("/{hotelId}")
    @ApiOperation(value = "Update active hotel by authorization [admin]", consumes = "application/json")
    public ResponseEntity<ResponseObject> changeActive(@PathVariable("hotelId") int hotelId, HttpServletRequest request) {
        return hotelService.changeActive(hotelId, request.getHeader("Authorization")) ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, true)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, false)
            );
    }

    @Override
    @PostMapping("")
    @ApiOperation(value = "add hotel by authorization [admin]", consumes = "application/json")
    public ResponseEntity<ResponseObject> postHotel(@RequestBody HotelPost form, HttpServletRequest request) {
        String res = hotelService.saveHotel(form, request.getHeader("Authorization"));
        return res.equals("true") ?    
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.INSERT_SUCCESS, "true")
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.INSERT_FAILED, false)
            );
    }

    
}