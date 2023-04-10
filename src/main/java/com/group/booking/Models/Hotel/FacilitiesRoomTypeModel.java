package com.group.booking.Models.Hotel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.group.booking.Models.Key.FacilitiesKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FacilitiesKey.class)
@Entity
@Table(name = "facilities_room_type")
public class FacilitiesRoomTypeModel {

    @Id
    private int roomTypeId;

    @Id
    private int facilitiesId;
}
