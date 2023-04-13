package com.group.booking.Services.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Models.Hotel.RoomTypeModel;
import com.group.booking.Repositories.Hotel.RoomTypeRepository;

@Service
public class RoomTypeService {
    
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public List<RoomTypeModel> findByHotelId(int hotelId, String checkin, String checkout) {
        try {
            List<RoomTypeModel> data = roomTypeRepository.findRoomTypeByHotelIdAndDate(hotelId, checkin, checkout);
            return !data.isEmpty() ? data : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RoomTypeModel findById(int id) {
        try {
            Optional<RoomTypeModel> found = roomTypeRepository.findById(id);
            if(found.isPresent()) return found.get();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
