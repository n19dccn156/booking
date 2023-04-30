package com.group.booking.Repositories.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Hotel.FacilitiesModel;

@Repository
public interface FacilitiesRepository extends JpaRepository<FacilitiesModel, Integer> {
    
    @Query(value = ""+
    "SELECT * "+
    "FROM facilities "+
    "WHERE "+
        "id NOT IN (SELECT facilities_id "+
                    "FROM facilities_hotel "+
                    "WHERE hotel_id = ?1)", nativeQuery = true)
    public List<FacilitiesModel> findAllByHotelIdInsert(int hotelId);
}
