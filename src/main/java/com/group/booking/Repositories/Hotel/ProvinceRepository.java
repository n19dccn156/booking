package com.group.booking.Repositories.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Hotel.ProvinceModel;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceModel, String> {
    
    Optional<ProvinceModel> findByIdAndName(String id, String name);
    Optional<ProvinceModel> findByName(String name);
    List<ProvinceModel> findByIdOrName(String id, String name);
}
