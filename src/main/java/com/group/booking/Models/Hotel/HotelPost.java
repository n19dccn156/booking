package com.group.booking.Models.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelPost {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String birthday;
    private String gender;
    private String name;
    private String address;
    private String checkin;
    private String checkout;
    private String province;
    private String hotelType;
    private String phoneHotel;
}
