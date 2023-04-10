package com.group.booking.Repositories.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
