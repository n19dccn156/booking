package com.group.booking.Services.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Models.Addons.OrderDetailResponse;
import com.group.booking.Repositories.Order.HotelOrderDetailRepository;

@Service
public class HotelOrderDetailService {
    
    @Autowired
    private HotelOrderDetailRepository hotelOrderDetailRepository;

    public List<OrderDetailResponse> getOrderDetailByOrderId(int orderId) {
        List<OrderDetailResponse> data = hotelOrderDetailRepository.getAllByOrderId(orderId);
        return !data.isEmpty() ? data : null;
    }
}
