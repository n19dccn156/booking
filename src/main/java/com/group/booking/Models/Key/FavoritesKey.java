package com.group.booking.Models.Key;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoritesKey implements Serializable{
    
    @Column(name = "users_id")
    private int userId;
    
    @Column(name = "hotels_id")
    private int hotelId;
}
