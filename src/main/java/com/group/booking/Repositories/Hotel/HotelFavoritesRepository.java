package com.group.booking.Repositories.Hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.booking.Models.Hotel.HotelFavoritesModel;
import com.group.booking.Models.Key.FavoritesKey;

@Repository
public interface HotelFavoritesRepository extends JpaRepository<HotelFavoritesModel, FavoritesKey> {
    
}
