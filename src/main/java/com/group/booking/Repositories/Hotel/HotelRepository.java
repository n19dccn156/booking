package com.group.booking.Repositories.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group.booking.Models.Addons.HotelResponse;
import com.group.booking.Models.Hotel.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Integer> {
    
    Optional<HotelModel> findByIdAndIsActive(int id, boolean isActive);
    Optional<HotelModel> findByUserIdAndIsActive(int userId, boolean isActive);
    
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

    @Transactional
    @Modifying
    @Query(value = "UPDATE hotels SET description = ?1 WHERE id = ?2", nativeQuery = true)
    public void updateByDescription(String description, Integer hotelId);

    @Transactional
    @Modifying
    @Query(value = ""+
        "UPDATE hotels "+
        "SET name = ?1,"+
            "address = ?2,"+
            "phone = ?3,"+
            "checkin = ?4,"+
            "checkout = ?5,"+
            "lat = ?6,"+
            "lon = ?7,"+
            "avatar = ?8 "+
        "WHERE id = ?9", nativeQuery = true)
    public void updateHotel(String name, String address, String phone, String checkin, String checkout, double lat, double lon, String avatar, int id);
}
