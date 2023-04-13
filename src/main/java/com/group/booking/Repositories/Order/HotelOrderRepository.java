package com.group.booking.Repositories.Order;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Order.HotelOrderModel;
import com.group.booking.Models.Order.OrderGroupByStatus;

@EnableJpaRepositories
@Repository
public interface HotelOrderRepository extends JpaRepository<HotelOrderModel, Integer> {
    
    List<HotelOrderModel> findAllByUserId(int hotelId);

    @Query(value = ""+
        "SELECT "+
            "status_id statusId, "+
            "(SELECT name FROM status st WHERE st.id = status_id) name, "+
            "COUNT(status_id) quantity "+
        "FROM hotel_orders "+
        "WHERE hotels_id = ?1 "+
        "GROUP BY status_id", nativeQuery = true)
    List<OrderGroupByStatus> findByHotelIdAndGroupByStatus(int hotelId);

    public Page<HotelOrderModel> findAllByHotelIdAndStatusIdOrderByCheckoutDesc(int hotelId, String statusId, Pageable pageable);
    public Page<HotelOrderModel> findAllByHotelIdAndStatusIdOrderByCheckinAsc(int hotelId, String statusId, Pageable pageable);

}
