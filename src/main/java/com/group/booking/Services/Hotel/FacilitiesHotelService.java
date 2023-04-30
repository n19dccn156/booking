package com.group.booking.Services.Hotel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Models.Addons.FacilitiesHotelResponse;
import com.group.booking.Models.Hotel.FacilitiesHotelModel;
import com.group.booking.Models.Hotel.HotelModel;
import com.group.booking.Repositories.Hotel.FacilitiesHotelRepository;

@Service
public class FacilitiesHotelService {
    
    @Autowired
    private FacilitiesHotelRepository facilitiesHotelRepository;
    @Autowired
    private HotelService hotelService;

    public List<FacilitiesHotelResponse> findByHotelId(int hotelId) {
        try {
            List<FacilitiesHotelResponse> data = facilitiesHotelRepository.findAllByHotelId(hotelId);
            return !data.isEmpty() ? data : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveForHotel(List<Integer> ids, String authorization) {
        try {
            HotelModel foundHotel = hotelService.foundByAuthorization(authorization);
            if(foundHotel != null) {
                ids.forEach(data -> {
                    facilitiesHotelRepository.save(new FacilitiesHotelModel(data, foundHotel.getId()));
                });
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteForHotel(int facilityId, String authorization) {
        try {
            HotelModel foundHotel = hotelService.foundByAuthorization(authorization);
            if(foundHotel != null) {
                FacilitiesHotelModel found = new FacilitiesHotelModel(facilityId, foundHotel.getId());
                facilitiesHotelRepository.delete(found);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
