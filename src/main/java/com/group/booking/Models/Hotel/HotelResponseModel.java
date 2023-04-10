package com.group.booking.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HotelResponseModel {
    
    @Id
    private int id;
    private String name;
    private String address;
    private String phone;
    private String checkin;
    private String checkout;
    private Double lat;
    private Double lon;
    @Column(name = "price_min")
    private double priceMin;
    @Column(name = "price_max")
    private double priceMax;
    private String avatar;
    private double rating;
    @Column(name = "num_rating")
    private int numRating;
    @Column(name = "hotel_type_name")
    private String hotelTypeName;
    @Column(name = "province_name")
    private String provinceName;
}
