package com.group.booking.Models.Hotel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class HotelModel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;

    @NotEmpty(message = Message.ADDRESS_NOT_EMP)
    private String address;

    @Pattern(regexp = "(^$|[0-9]{10-11}$)", message = Message.PHONE_VALIDATE)
    @NotEmpty(message = Message.PHONE_NOT_EMP)
    private String phone;

    @NotEmpty(message = Message.CHECKIN_NOT_EMP)
    private String checkin;

    @NotEmpty(message = Message.CHECKOUT_NOT_EMP)
    private String checkout;

    @NotEmpty(message = Message.LAT_NOT_EMP)
    private Double lat;

    @NotEmpty(message = Message.LON_NOT_EMP)
    private Double lon;

    @Column(name = "price_min")
    private double priceMin;

    @Column(name = "price_max")
    private double priceMax;    
    
    @NotEmpty(message = Message.DESCRIPTION_NOT_EMP)
    private String description;

    @NotEmpty(message = Message.AVATAR_NOT_EMP)
    private String avatar;

    private double rating;
    
    @Column(name = "num_rating")
    private int numRating;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "user_id", unique = true)
    private int userId;

    @Column(name = "hotel_type_id")
    private String typeHotelId;

    @Column(name = "province_id")
    private String provinceId;

    @JsonManagedReference
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<RoomTypeModel> roomTypes;
}
