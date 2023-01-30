package com.be.booking.Models.OrderHotel;

import java.util.Date;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders_hotel")
public class OrderHotelModel {
    
    @Id
    private ObjectId _id;
    private Date checkin;
    private Date checkout;
    private Date created;
    private String name;
    private String phone;
    private ObjectId userId;
    private ObjectId hotelId;
    private String statusId;
    private RatingModel rating;
    private Set<RoomOrder> rooms;
}
