package com.be.booking.Repositories.Account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.Account.FavoriteHotelModel;

@Repository
public interface FavoriteRepository extends MongoRepository<FavoriteHotelModel, String>{
    
}
