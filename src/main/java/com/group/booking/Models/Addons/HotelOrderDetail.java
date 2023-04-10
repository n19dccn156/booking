package com.group.booking.Models.Addons;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelOrderDetail {
       
    private int hotelOrderId;
    private int roomTypeId;

    @Size(min = 200000, message = Message.PRICE_VALIDATE)
    @NotEmpty(message = Message.PRICE_NOT_EMP)
    private double price;

    @Size(min = 1, message = Message.QUANTITY_VALIDATE)
    @NotEmpty(message = Message.QUANTITY_NOT_EMP)
    private int quantity;

}
