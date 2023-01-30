package com.be.booking.Repositories.Hotel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.Hotel.HotelModel;

@Repository
public interface HotelRepository extends MongoRepository<HotelModel, String>{
    
}
