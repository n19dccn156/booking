package com.group.booking.Models.Addons;

public interface HotelResponse {
    
    int getId();
    String getName();
    String getAddress();
    String getPhone();
    String getCheckin();
    String getCheckout();
    Double getLat();
    Double getLon();
    double getPriceMin();
    double getPriceMax();
    String getAvatar();
    double getRating();
    int getNumRating();
    String getHotelTypeName();
    String getProvinceName();
}
