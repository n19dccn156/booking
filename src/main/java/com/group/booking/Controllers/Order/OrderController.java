package com.group.booking.Controllers.Order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Interfaces.Order.HotelOrderImpl;
import com.group.booking.Models.Addons.OrderRequest;
import com.group.booking.Models.Addons.ResponseObject;
import com.group.booking.Models.Order.HotelOrderModel;
import com.group.booking.Models.Order.OrderGroupByStatus;
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
    @ApiOperation(value = "Post order [ALL]", consumes = "application/json")
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
    @PutMapping("/{id}")
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

    @Override
    @GetMapping("/group-status")
    @ApiOperation(value = "Get orders by hotel id [HOTEL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getOrderByHotelId(HttpServletRequest request) {
        List<OrderGroupByStatus> foundOrders = hotelOrderService.getByHotelIdAndGroupByHotelId(request.getHeader("Authorization"));
        if(foundOrders != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundOrders)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
        }
    }

    @Override
    @GetMapping("")
    @ApiOperation(value = "Get orders by hotel id and status [HOTEL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getOrderByHotelIdAndStatus(
        HttpServletRequest request,
        @RequestParam(name = "status", required = true) String status,
        @RequestParam(name = "page", required = true) int page,
        @RequestParam(name = "size", required = true) int size) {

        Page<HotelOrderModel> foundOrders = hotelOrderService.getOrderByHotelIdAndStatus(request.getHeader("Authorization"), status, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundOrders)
        );
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Get order by id [All]", consumes = "application/json")
    public ResponseEntity<ResponseObject> getOrderById(@PathVariable("id") int id) {
        HotelOrderModel foundHotel = hotelOrderService.getByOrderId(id);
        return foundHotel != null ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.SELECT_SUCCESS, foundHotel)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(Const.STATUS_FAILED, Message.SELECT_FAILED, "")
            );
    }

    @Override
    // @PatchMapping("/{id}")
    // @ApiOperation(value = "Cancel order by order id [All]", consumes = "application/json")
    public ResponseEntity<ResponseObject> cancelOrderByManage(@PathVariable("id") int orderId, HttpServletRequest request) {
        boolean data = hotelOrderService.cancelOrderForManage(orderId, request.getHeader("Authorization"));
        return data == true ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, request)  
            )
            :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, request)  
            );
    }

    @Override
    @PutMapping("/{id}/status/{status}")
    @ApiOperation(value = "Cancel order by order id [HOTEL]", consumes = "application/json")
    public ResponseEntity<ResponseObject> changeStatus(
        @PathVariable("id") int orderId, @PathVariable("status") String status, HttpServletRequest request) {
        boolean data = hotelOrderService.changeStatus(orderId, status, request.getHeader("Authorization"));
        return data == true ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(Const.STATUS_SUCCESS, Message.UPDATE_SUCCESS, data)  
            )
            :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject(Const.STATUS_FAILED, Message.UPDATE_FAILED, data)  
            );
    }
    
    
    
}
