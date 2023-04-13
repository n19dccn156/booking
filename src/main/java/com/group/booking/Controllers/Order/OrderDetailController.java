package com.group.booking.Controllers.Order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Order.HotelOrderDetailImp;
import com.group.booking.Models.Addons.OrderDetailResponse;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Services.Order.HotelOrderDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/order-detail")
@Api(tags = "Order Details")
public class OrderDetailController implements HotelOrderDetailImp {
    
    @Autowired
    private HotelOrderDetailService hotelOrderDetailService;

    @Override
    @GetMapping("/{orderId}")
    @ApiOperation(value = "get orders by order ID [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getOrderByOrderId(@PathVariable("orderId") int userId) {
        List<OrderDetailResponse> foundOrder = hotelOrderDetailService.getOrderDetailByOrderId(userId);
        return foundOrder != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundOrder)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

}
