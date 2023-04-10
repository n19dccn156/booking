package com.group.booking.Repositories.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Hotel.FacilitiesRoomTypeModel;
import com.group.booking.Models.Key.FacilitiesKey;

@Repository
public interface FacilitiesRoomTypeRepository extends JpaRepository<FacilitiesRoomTypeModel, FacilitiesKey> {
    
}
