package com.group.booking.Models.Key;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilitiesHotelKey implements Serializable {
    
    @Column(name = "facilities_id")
    private int facilitiesId;
    
    @Column(name = "hotel_id")
    private int hotelId;
}
