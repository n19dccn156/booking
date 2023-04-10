package com.group.booking.Repositories.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Order.HotelOrderModel;

@EnableJpaRepositories
@Repository
public interface HotelOrderRepository extends JpaRepository<HotelOrderModel, Integer> {
    
    List<HotelOrderModel> findAllByUserId(int hotelId);
}
