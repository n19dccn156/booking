package com.group.booking.Repositories.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Addons.OrderDetailResponse;
import com.group.booking.Models.Key.OrderKey;
import com.group.booking.Models.Order.HotelOrderDetailModel;

@Repository
public interface HotelOrderDetailRepository extends JpaRepository<HotelOrderDetailModel, OrderKey>{
    
    @Modifying
    @Query(value = "" +
        "INSERT INTO hotel_order_details(hotel_orders_id, room_type_id, quantity, price) " +
        "VALUES(?1, ?2, ?3, ?4)"
    , nativeQuery = true)
    public void saveByOrder(int orderId, int roomTypeId, int quantity, double price);

    @Query(value = "" +
        "SELECT "+
            "room_type_id roomTypeId, "+
            "hotel_orders_id orderId, "+
            "(SELECT name FROM room_type rt WHERE rt.id = room_type_id) roomName, "+
            "(SELECT DATEDIFF(checkout, checkin) FROM hotel_orders ho WHERE ho.id = ?1) numDay," +
            "price, "+
            "quantity "+
        "FROM hotel_order_details "+
        "WHERE hotel_orders_id = ?1"+
    "", nativeQuery = true)
    public List<OrderDetailResponse> getAllByOrderId(int orderId);
}
