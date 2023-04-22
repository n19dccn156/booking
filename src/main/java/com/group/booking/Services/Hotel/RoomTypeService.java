package com.group.booking.Services.Hotel;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Common.Const;
import com.group.booking.Models.Hotel.HotelModel;
import com.group.booking.Models.Hotel.RoomTypeModel;
import com.group.booking.Repositories.Hotel.RoomTypeRepository;
import com.group.booking.Services.Image.ImageService;

@Service
public class RoomTypeService {
    
    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ImageService imageService;

    public List<RoomTypeModel> findByHotelId(int hotelId, String checkin, String checkout) {
        try {
            List<RoomTypeModel> data = roomTypeRepository.findRoomTypeByHotelIdAndDate(hotelId, checkin, checkout);
            return !data.isEmpty() ? data : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<RoomTypeModel> findByAuthorization(String authorization) {
        try {
            HotelModel hotel = hotelService.foundByAuthorization(authorization);
            if(hotel != null) {
                return hotel.getRoomTypes();
            }
        } catch (Exception e) {
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

    public RoomTypeModel updateById(RoomTypeModel roomType, String authorization) {
        HotelModel foundHotel = hotelService.foundByAuthorization(authorization);
        if(foundHotel != null) {
            
            for (RoomTypeModel r : foundHotel.getRoomTypes()) {
                if(r.getId() == roomType.getId()) {
                    RoomTypeModel room = r;
                    room.setName(roomType.getName());
                    room.setQuantity(roomType.getQuantity());
                    room.setPrice(roomType.getPrice());
                    room.setActive(roomType.isActive());

                    if(!room.getAvatar().equals(roomType.getAvatar())) {
                        imageService.putImage(Const.HOST+room.getAvatar(), Base64.getDecoder().decode(roomType.getAvatar()), authorization);
                    }

                    roomTypeRepository.save(room);
                    return room;
                }
            }
        }
        return null;
    }

    public RoomTypeModel postRoomType(RoomTypeModel room, String authorization) {
        try {
            HotelModel foundHotel = hotelService.foundByAuthorization(authorization);
            if(foundHotel != null) {
                String url = imageService.postImage(Base64.getDecoder().decode(room.getAvatar()), authorization);
                room.setAvatar(url);
                room.setActive(true);
                room.setHotelId(foundHotel.getId());
                roomTypeRepository.save(room);

                return room;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}

