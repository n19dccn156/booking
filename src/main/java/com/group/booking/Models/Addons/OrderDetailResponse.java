package com.group.booking.Models.Addons;

public interface OrderDetailResponse {
    
    public int getOrderId();
    public int getRoomTypeId();
    public String getRoomName();
    public int getNumDay();
    public int getQuantity();
    public double getPrice();
}
