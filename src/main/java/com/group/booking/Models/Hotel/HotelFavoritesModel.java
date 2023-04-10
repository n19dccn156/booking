package com.group.booking.Models.Hotel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.group.booking.Models.Key.FavoritesKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "hotel_favorites")
@IdClass(FavoritesKey.class)
public class HotelFavoritesModel {
    
    @Id
    private int userId;

    @Id
    private int hotelId;
}
