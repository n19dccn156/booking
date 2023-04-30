package com.group.booking.Models.Key;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageKey implements Serializable {
    
    @Column(name = "images_id")
    private int imageId;
    
    @Column(name = "hotels_id")
    private int hotelId;
}
