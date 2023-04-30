package com.group.booking.Services.Hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Models.Hotel.FacilitiesModel;
import com.group.booking.Repositories.Hotel.FacilitiesRepository;

@Service
public class FacilitiesService {
    
    @Autowired
    private FacilitiesRepository facilitiesRepository;

    public List<FacilitiesModel> findAll() {
        try {
            List<FacilitiesModel> founds = facilitiesRepository.findAll();
            if(!founds.isEmpty()) return founds;
        } catch (Exception e) {}
        return null;
    }

    public List<FacilitiesModel> findByHotelIdToInsert(int hotelId) {
        try {
            List<FacilitiesModel> data = facilitiesRepository.findAllByHotelIdInsert(hotelId);
            return !data.isEmpty() ? data : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
