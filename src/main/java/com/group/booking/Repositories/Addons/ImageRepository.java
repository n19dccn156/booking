package com.group.booking.Repositories.Addons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Addons.ImagesModel;

@Repository
public interface ImageRepository extends JpaRepository<ImagesModel, Integer> {
    
}
