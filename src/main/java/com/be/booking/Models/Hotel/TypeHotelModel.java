package com.be.booking.Models.Hotel;

import javax.validation.constraints.NotEmpty;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "types_hotel")
public class TypeHotelModel {
    
    @Id
    private ObjectId _id;
    @NotEmpty(message = "Tên mô hình khách sạn không được bỏ trống")
    private String name;
}
