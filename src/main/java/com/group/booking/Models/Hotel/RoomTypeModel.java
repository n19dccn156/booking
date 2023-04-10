package com.group.booking.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_type")
public class RoomTypeModel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;

    @Column(nullable = false)
    @Size(min = 200000, message = Message.PRICE_VALIDATE)
    @NotEmpty(message = Message.PRICE_NOT_EMP)
    private double price;
    
    @Column(nullable = false)
    @Size(min = 1, message = Message.QUANTITY_NOT_EMP)
    @NotEmpty(message = Message.QUANTITY_NOT_EMP)
    private int quantity;

    private String avatar;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "hotels_id")
    private int hotelId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotels_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private HotelModel hotel;
}
