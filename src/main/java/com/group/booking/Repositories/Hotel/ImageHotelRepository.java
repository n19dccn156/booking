package com.group.booking.Repositories.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Hotel.ImageHotelModel;
import com.group.booking.Models.Key.ImageKey;

@Repository
public interface ImageHotelRepository extends JpaRepository<ImageHotelModel, ImageKey> {
    
    List<ImageHotelModel> findByHotelId(int hotelId);
}
