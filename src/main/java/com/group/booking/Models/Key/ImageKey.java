package com.group.booking.Models.Key;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageKey implements Serializable {
    
    @Column(name = "images_id")
    private int imageId;
    
    @Column(name = "hotels_id")
    private int hotelId;
}
