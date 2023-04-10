package com.group.booking.Repositories.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Addons.HotelResponse;
import com.group.booking.Models.Hotel.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Integer> {
    
    Optional<HotelModel> findByIdAndIsActive(int id, boolean isActive);
    
    @Query(value = "SELECT "+
                    "id, "+
                    "name, "+
                    "address, "+
                    "phone, "+
                    "checkin, "+
                    "checkout, "+
                    "lat, "+
                    "lon, "+
                    "price_min priceMin, "+
                    "price_max priceMax, "+
                    "avatar, "+
                    "rating, "+
                    "num_rating numRating, "+
                    "(SELECT name from hotel_types WHERE id = hotels.hotel_type_id) as hotelTypeName, " +
                    "(SELECT name from provinces WHERE id = hotels.province_id) as provinceName " +
                    "FROM hotels ORDER BY rating DESC limit 8", nativeQuery = true)
    public List<HotelResponse> findTop8OrderByRating();

    @Query(value = "SELECT "+
                    "id, "+
                    "name, "+
                    "address, "+
                    "phone, "+
                    "checkin, "+
                    "checkout, "+
                    "lat, "+
                    "lon, "+
                    "price_min priceMin, "+
                    "price_max priceMax, "+
                    "avatar, "+
                    "rating, "+
                    "num_rating numRating, "+
                    "(SELECT name from hotel_types WHERE id = hotels.hotel_type_id) as hotelTypeName, " +
                    "(SELECT name from provinces WHERE id = hotels.province_id) as provinceName " +
                    "FROM hotels ORDER BY num_rating DESC limit 8", nativeQuery = true)
    public List<HotelResponse> findTop8OrderByNumberRating();

}
