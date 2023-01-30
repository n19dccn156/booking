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
@Document(collection = "facilites")
public class FaciliteModel {
    
    @Id
    private ObjectId _id;
    @NotEmpty(message = "Tên tiện ích không được bỏ trống")
    private String name;
    @NotEmpty(message = "Tên icon-web không được bỏ trống")
    private String iconWeb;
    @NotEmpty(message = "Tên icon-mobile không được bỏ trống")
    private String iconMob;
}
