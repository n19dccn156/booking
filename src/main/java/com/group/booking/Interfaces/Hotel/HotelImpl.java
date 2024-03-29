package com.group.booking.Interfaces.Hotel;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.HotelPost;
import com.group.booking.Models.Hotel.HotelUpdate;

public interface HotelImpl {
    
    public ResponseEntity<ResponseObject> getById(int id);
    public ResponseEntity<ResponseObject> getAll(int page, int size);
    public ResponseEntity<ResponseObject> getTop8ByRating();
    public ResponseEntity<ResponseObject> getTop8ByNumberRating();
    public ResponseEntity<ResponseObject> getTop8ByTrending();
    public ResponseEntity<ResponseObject> getListHotelsByFilter(HashMap<String, String> filters);
    public ResponseEntity<ResponseObject> getImagesByHotelId(int hotelId);
    public ResponseEntity<ResponseObject> getCommentsByHotelId(int hotelId, int page, int size);
    public ResponseEntity<ResponseObject> GetRevenueOn12MonthAgo(HttpServletRequest request);
    public ResponseEntity<ResponseObject> getPercentByRoomType(HttpServletRequest request);
    public ResponseEntity<ResponseObject> getHotelByAuthorization(HttpServletRequest request);
    public ResponseEntity<ResponseObject> getDescriptionByAuthorization(HttpServletRequest request);
    public ResponseEntity<ResponseObject> getDescriptionByAuthorization(String description, HttpServletRequest request);
    public ResponseEntity<ResponseObject> updateHotel(HotelUpdate hotel, HttpServletRequest request);
    public ResponseEntity<ResponseObject> changeActive(int hotelId, HttpServletRequest request);
    public ResponseEntity<ResponseObject> postHotel(HotelPost model, HttpServletRequest request);
}
