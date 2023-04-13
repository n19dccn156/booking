package com.group.booking.Repositories.Hotel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Hotel.RoomTypeModel;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeModel, Integer> {
 
    //     SELECT rt.id, rt.name, rt.avatar, rt.price, (rt.quantity-r.quantity) quantity, is_active, hotels_id FROM room_type rt
// INNER JOIN ( SELECT room_type_id id, SUM(quantity) quantity 
// 			FROM hotel_order_details
// 			WHERE hotel_orders_id IN (
// 				SELECT id FROM hotel_orders
// 				WHERE hotels_id = 0 and 
// 				((checkin >= '2001-03-11' and checkin < '2001-03-11') or 
// 				(checkout > '2001-03-11' and checkout <= '2001-03-11')))
// 			GROUP BY room_type_id) r
// ON rt.id = r.id and is_active = true

    @Query(value = ""+
        "SELECT "+
            "id, "+
            "name, "+
            "price, "+
            "avatar, "+
            "is_active, "+
            "hotels_id, "+
            "quantity - "+ 
                "IFNULL((SELECT SUM(quantity) "+
                "FROM hotel_order_details "+
                "WHERE hotel_orders_id IN( "+
                    "SELECT  "+
                        "id  "+
                    "FROM  "+
                        "hotel_orders  "+
                    "WHERE  "+
                        "hotels_id = rt.hotels_id AND "+
                        "((checkin >= ?2 and checkin < ?3) OR "+
                        "(checkout > ?2 and checkout <= ?3)) AND "+
                        "status_id != 'DAHUY'"+
                ") AND room_type_id = rt.id "+
                "GROUP BY room_type_id), 0) quantity "+
        "FROM room_type rt " +
        "WHERE hotels_id = ?1 and is_active = true", nativeQuery = true)
    public List<RoomTypeModel> findRoomTypeByHotelIdAndDate(int hotel_id, String checkin, String Checkout);
}
