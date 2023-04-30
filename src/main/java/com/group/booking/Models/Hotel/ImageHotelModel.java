package com.group.booking.Models.Hotel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.group.booking.Models.Key.ImageKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "images_hotel")
@IdClass(ImageKey.class)
public class ImageHotelModel {

    @Id
    private int hotelId;

    @Id
    private int imageId;

    private String url;
}
