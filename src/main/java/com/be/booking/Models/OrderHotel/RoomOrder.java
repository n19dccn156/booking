package com.be.booking.Models.OrderHotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomOrder {
    
    private String roomId;
    private int price;
    private int quantity;
}
