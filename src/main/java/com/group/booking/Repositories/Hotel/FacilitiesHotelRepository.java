package com.group.booking.Repositories.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Addons.FacilitiesHotelResponse;
import com.group.booking.Models.Hotel.FacilitiesHotelModel;
import com.group.booking.Models.Key.FacilitiesHotelKey;

@Repository
public interface FacilitiesHotelRepository extends JpaRepository<FacilitiesHotelModel, FacilitiesHotelKey> {
    
    @Query(value = ""+
    "SELECT "+
        "fh.facilities_id facilitiesId, "+
        "fh.hotel_id hotelId, "+
        "f.name, "+
        "f.icon_web iconWeb, "+
        "f.icon_mob iconMob "+
    "FROM " + 
        "(SELECT * FROM facilities_hotel WHERE hotel_id = 1) fh " +
    "INNER JOIN facilities f " +
    "ON fh.facilities_id = f.id", nativeQuery = true)
    public List<FacilitiesHotelResponse> findAllByHotelId(int hotelId);

}
