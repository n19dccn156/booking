package com.be.booking.Repositories.Account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.Account.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
    
}
