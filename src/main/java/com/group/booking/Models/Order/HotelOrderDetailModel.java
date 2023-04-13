package com.group.booking.Models.Order;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group.booking.Common.Message;
import com.group.booking.Models.Addons.OrderDetailRequest;
import com.group.booking.Models.Key.OrderKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel_order_details")
@IdClass(OrderKey.class)
public class HotelOrderDetailModel {
    
    @Id
    private int hotelOrderId;
    @Id
    private int roomTypeId;

    @Size(min = 200000, message = Message.PRICE_VALIDATE)
    @NotEmpty(message = Message.PRICE_NOT_EMP)
    private double price;

    @Size(min = 1, message = Message.QUANTITY_VALIDATE)
    @NotEmpty(message = Message.QUANTITY_NOT_EMP)
    private int quantity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_orders_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private HotelOrderModel hotelOrder;

    public HotelOrderDetailModel(OrderDetailRequest orderDetail, int orderId, double price) {
        this.hotelOrderId = orderId;
        this.roomTypeId = orderDetail.getRoomId();
        this.price = price;
        this.quantity = orderDetail.getQuantity();
    }

    public HotelOrderDetailModel(int orderId, int roomTypeId, double price, int quantity) {
        this.hotelOrderId = orderId;
        this.roomTypeId = roomTypeId;
        this.price = price;
        this.quantity = quantity;
    }
}
