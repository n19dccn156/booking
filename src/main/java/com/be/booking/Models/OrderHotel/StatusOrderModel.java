package com.be.booking.Models.OrderHotel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "status")
public class StatusOrderModel {
    
    @Id
    private String _id;
    private String name;
    private int index;
}
