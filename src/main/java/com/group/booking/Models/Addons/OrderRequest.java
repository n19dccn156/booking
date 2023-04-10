package com.group.booking.Models.Addons;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @Column(nullable = false)
    @NotEmpty(message = Message.CHECKIN_NOT_EMP)
    private String checkin;

    @Column(nullable = false)
    @NotEmpty(message = Message.CHECKOUT_NOT_EMP)
    private String checkout;

    @Column(name = "create_at", nullable = false, length = 20)
    private String createdAt;

    @Column(name = "modify_time", nullable = false, length = 20)
    private String modifyTime;
    
    @Column(nullable = false, length = 50)
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;

    @Column(nullable = false, length = 10)
    @NotEmpty(message = Message.PHONE_NOT_EMP)
    private String phone;

    @Column(name = "users_id", nullable = false)
    private int userId;

    @Column(name = "hotels_id", nullable = false)
    private int hotelId;
    
    @NotNull(message = "Phòng đặt không bỏ trống")
    private Set<HotelOrderDetail> orderDetails;
    
}
