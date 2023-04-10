package com.group.booking.Services.Hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Models.Addons.FacilitiesHotelResponse;
import com.group.booking.Repositories.Hotel.FacilitiesHotelRepository;

@Service
public class FacilitiesHotelService {
    
    @Autowired
    private FacilitiesHotelRepository facilitiesHotelRepository;

    public List<FacilitiesHotelResponse> findByHotelId(int hotelId) {
        try {
            List<FacilitiesHotelResponse> data = facilitiesHotelRepository.findAllByHotelId(hotelId);
            return !data.isEmpty() ? data : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
