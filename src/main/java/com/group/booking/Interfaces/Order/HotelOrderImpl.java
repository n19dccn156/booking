package com.group.booking.Interfaces.Order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.OrderRequest;
import com.group.booking.Models.Addons.ResponseObject;

public interface HotelOrderImpl {
    
    public ResponseEntity<ResponseObject> getOrderByUserId(int userId);
    public ResponseEntity<ResponseObject> postOrder(OrderRequest model);
    public ResponseEntity<ResponseObject> cancelOrder(int orderId, HttpServletRequest request);
    public ResponseEntity<ResponseObject> getOrderByHotelId(HttpServletRequest request);
    public ResponseEntity<ResponseObject> getOrderByHotelIdAndStatus(HttpServletRequest request, String status, int page, int size);
    public ResponseEntity<ResponseObject> getOrderById(int id);
    public ResponseEntity<ResponseObject> cancelOrderByManage(int orderId, HttpServletRequest request);
    public ResponseEntity<ResponseObject> changeStatus(int orderId, String status, HttpServletRequest request);
    public ResponseEntity<ResponseObject> confirmOrder(String orders);
    public ResponseEntity<ResponseObject> patchCommentsByOrderIdAndAuthz(int orderId, int rating, String comment, HttpServletRequest request);

}
