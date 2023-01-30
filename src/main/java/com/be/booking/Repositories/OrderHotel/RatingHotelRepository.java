package com.be.booking.Repositories.OrderHotel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.OrderHotel.RatingModel;

@Repository
public interface RatingHotelRepository extends MongoRepository<RatingModel, String>{
    
}
