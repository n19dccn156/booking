package com.group.booking.Models.Hotel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.group.booking.Models.Key.FacilitiesHotelKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FacilitiesHotelKey.class)
@Entity
@Table(name = "facilities_hotel")
public class FacilitiesHotelModel {
    
    @Id
    private int facilitiesId;
    @Id
    private int hotelId;
}
