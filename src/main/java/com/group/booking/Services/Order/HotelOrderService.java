package com.group.booking.Services.Order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.group.booking.Common.Const;
import com.group.booking.Models.Addons.OrderRequest;
import com.group.booking.Models.Order.HotelOrderDetailModel;
import com.group.booking.Models.Order.HotelOrderModel;
import com.group.booking.Repositories.Order.HotelOrderDetailRepository;
import com.group.booking.Repositories.Order.HotelOrderRepository;
import com.group.booking.Utils.JwtUltil;

@Service
public class HotelOrderService {
    
    @Autowired
    private HotelOrderRepository hotelOrderRepository;
    @Autowired
    private HotelOrderDetailRepository hotelOrderDetailRepository;
    @Autowired
    private JwtUltil jwtUltil;

    public HotelOrderModel getByOrderId(int orderId) {
        Optional<HotelOrderModel> foundOrder = hotelOrderRepository.findById(orderId);
        return foundOrder.isPresent() ? foundOrder.get() : null;
    }

    public List<HotelOrderModel> getAllByUserId(int hotelId) {
        try {
            List<HotelOrderModel> data = hotelOrderRepository.findAllByUserId(hotelId);
            return !data.isEmpty() ? data : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public HotelOrderModel postOrder(OrderRequest order) {
        try {
            HotelOrderModel orderReq = new HotelOrderModel(order);
            // save
            orderReq = hotelOrderRepository.save(orderReq);   
            HotelOrderModel orderRes = hotelOrderRepository.save(orderReq);
            order.getOrderDetails().forEach(data -> {
                hotelOrderDetailRepository.saveByOrder(orderRes.getId(), data.getRoomTypeId(), data.getQuantity(), data.getPrice());
            });
            // return
            orderReq.setOrderDetails(order.getOrderDetails().stream().map((obj) -> new HotelOrderDetailModel(obj, orderRes.getId())).collect(Collectors.toSet()));

            return orderReq;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    public boolean cancelOrder(int orderId, String authorization) {
        try {
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(!userId.equals("")) {
                HotelOrderModel foundOrder = getByOrderId(orderId);
                if(foundOrder != null && foundOrder.getUserId() == Integer.valueOf(userId)) {
                    foundOrder.setStatusId(Const.CANCEL);
                    hotelOrderRepository.save(foundOrder);
                    return true;
                }
            }
        } catch (Exception e) {
            
        }
        return false;
    }
    
}
