package com.be.booking.Repositories.Hotel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.Hotel.TypeHotelModel;

@Repository
public interface TypeHotelRepository extends MongoRepository<TypeHotelModel, String>{
    
}
