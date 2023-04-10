package com.group.booking.Interfaces.Hotel;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ResponseObject;

public interface HotelImpl {
    
    public ResponseEntity<ResponseObject> getById(int id);
    public ResponseEntity<ResponseObject> getTop8ByRating();
    public ResponseEntity<ResponseObject> getTop8ByNumberRating();
    public ResponseEntity<ResponseObject> getTop8ByTrending();
    public ResponseEntity<ResponseObject> getListHotelsByFilter(HashMap<String, String> filters);
    public ResponseEntity<ResponseObject> getImagesByHotelId(int hotelId);
    public ResponseEntity<ResponseObject> getCommentsByHotelId(int hotelId, int page, int size);

}