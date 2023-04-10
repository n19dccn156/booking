package com.group.booking.Models.Key;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderKey implements Serializable {
    
    @Column(name = "room_type_id")
    private int roomTypeId;
    
    @Column(name = "hotel_orders_id")
    private int hotelOrderId;
}
