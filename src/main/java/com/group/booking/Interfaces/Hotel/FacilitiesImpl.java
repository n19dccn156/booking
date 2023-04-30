package com.group.booking.Interfaces.Hotel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ListObject;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Hotel.FacilitiesModel;

public interface FacilitiesImpl {

    public ResponseEntity<ResponseObject> findAll();
    public ResponseEntity<ResponseObject> findAllForInsert(int hotelId);
    public ResponseEntity<ResponseObject> findByHotelId(int hotelId);
    public ResponseEntity<ResponseObject> deleteForHotel(int id, HttpServletRequest request);
    public ResponseEntity<ResponseObject> deleteForManager(int id, HttpServletRequest request);
    public ResponseEntity<ResponseObject> saveForHotel(ListObject ids, HttpServletRequest request);
    public ResponseEntity<ResponseObject> saveForManager(FacilitiesModel facility, HttpServletRequest request);
    // public ResponseEntity<ResponseObject> update(FacilitiesModel facility, HttpServletRequest request);
}
