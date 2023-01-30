package com.be.booking.Repositories.Hotel;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.be.booking.Models.Hotel.RoomModel;

@Repository
public interface RoomRepository extends MongoRepository<RoomModel, String>{
    
    /**
     * List Rooms by hotel_id
     * @return
     */
    @Query(value = "{_id: {$in: ?0}}")
    List<RoomModel> findAllByListRoomId(List<ObjectId> listId);

}
