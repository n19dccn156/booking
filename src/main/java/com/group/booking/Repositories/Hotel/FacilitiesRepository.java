package com.group.booking.Repositories.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Hotel.FacilitiesModel;

@Repository
public interface FacilitiesRepository extends JpaRepository<FacilitiesModel, Integer> {
    
}
