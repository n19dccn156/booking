package com.group.booking.Repositories.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group.booking.Models.Addons.HotelResponse;
import com.group.booking.Models.Hotel.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Integer> {
    
    Optional<HotelModel> findById(int id);
    Optional<HotelModel> findByIdAndIsActive(int id, boolean isActive);
    Optional<HotelModel> findByUserIdAndIsActive(int userId, boolean isActive);
    Page<HotelModel> findAll(Pageable page);

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
    @Query(value = "UPDATE hotels SET is_active = ?2 WHERE id = ?1", nativeQuery = true)
    public void updateActive(int hotelId, boolean active);

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

    @Transactional
    @Modifying
    @Query(value = ""+
        "INSERT INTO "+
        "hotels (name, address, phone, checkin, checkout, avatar, province_id, "+
        "hotel_type_id, user_id, is_active, lat, lon, price_min, price_max, rating, num_rating, description) "+
        "VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, 10.0, 109.0, 100000.0, 100000.0, 0.0, 0, ?11)",nativeQuery = true)
    public void insertHotel(String name, String address, String phone, String checkin, String checkout, 
                        String avatar, String province, String hotelType, int userId, boolean isActive, String description);
}
