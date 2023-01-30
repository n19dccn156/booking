package com.be.booking.Repositories.OrderHotel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.OrderHotel.StatusOrderModel;

@Repository
public interface StatusOrderRepository extends MongoRepository<StatusOrderModel, String>{
    
}
