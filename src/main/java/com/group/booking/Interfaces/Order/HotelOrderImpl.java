package com.group.booking.Interfaces.Order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.OrderRequest;
import com.group.booking.Models.Addons.ResponseObject;

public interface HotelOrderImpl {
    
    public ResponseEntity<ResponseObject> getOrderByUserId(int userId);
    public ResponseEntity<ResponseObject> postOrder(OrderRequest model);
    public ResponseEntity<ResponseObject> cancelOrder(int orderId, HttpServletRequest request);
}
