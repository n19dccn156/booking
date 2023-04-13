package com.group.booking.Models.Addons;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PrecentByRoomType {
    
    @Id
    private int id;
    private String name;
    private String quantity;
}
