package com.be.booking.Repositories.OrderHotel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.OrderHotel.OrderHotelModel;

@Repository
public interface OrderHotelRepository extends MongoRepository<OrderHotelModel, String>{
    
}
