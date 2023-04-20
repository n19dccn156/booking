package com.group.booking.Models.Hotel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HotelUpdate {
    
    private int id;
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;
    @NotEmpty(message = Message.AVATAR_NOT_EMP)
    private String avatar;
    @NotEmpty(message = Message.ADDRESS_NOT_EMP)
    private String address;
    
    @NotEmpty(message = Message.PHONE_NOT_EMP)
    @Pattern(regexp = "(^$|[0-9]{10}$)", message = Message.PHONE_VALIDATE)
    private String phone;

    private double rating;
    @NotEmpty(message = Message.CHECKIN_NOT_EMP)
    private String checkin;
    @NotEmpty(message = Message.CHECKOUT_NOT_EMP)
    private String checkout;

    private double lat;
    private double lon;
    private int userId;
    private boolean active;

}
