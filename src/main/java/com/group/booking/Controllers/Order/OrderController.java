package com.group.booking.Controllers.Order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Order.HotelOrderImpl;
import com.group.booking.Models.Addons.OrderRequest;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Order.HotelOrderModel;
import com.group.booking.Services.Order.HotelOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/order")
@Api(tags = "Order")
public class OrderController implements HotelOrderImpl {

    @Autowired
    private HotelOrderService hotelOrderService;

    @Override
    @GetMapping("/user/{userId}")
    @ApiOperation(value = "get orders by User ID [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getOrderByUserId(@PathVariable("userId") int userId) {
        List<HotelOrderModel> foundOrder = hotelOrderService.getAllByUserId(userId);
        return foundOrder != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundOrder)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    @PostMapping(name = "", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "post order [ALL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> postOrder(@RequestBody @Validated OrderRequest model) {
        HotelOrderModel data = hotelOrderService.postOrder(model);
        return data != null ?
            ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.INSERT_SUCCESS, data)
            )
            :
            ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(
                new ResponseObject(Const.STATUS_FAILED, Message.INSERT_FAILED, "")
            );
    }

    @Override
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete orders by order ID [Authenticate]", consumes = "application/json")
    public ResponseEntity<ResponseObject> cancelOrder(@PathVariable("id") int orderId, HttpServletRequest request) {
        if(hotelOrderService.cancelOrder(orderId, request.getHeader("Authorization"))) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, true)
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new ResponseObject(Const.STATUS_SUCCESS, Message.UNAUTHORIZED, false)
        );
    }
    
    
}
