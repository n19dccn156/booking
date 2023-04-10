package com.group.booking.Models.Key;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilitiesKey implements Serializable {
    
    @Column(name = "facilities_id")
    private int facilitiesId;
    
    @Column(name = "room_type_id")
    private int roomTypeId;
}
