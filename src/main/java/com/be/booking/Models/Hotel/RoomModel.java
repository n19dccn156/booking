package com.be.booking.Models.Hotel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// import org.bson.types.ObjectId;
// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
// @Document(collection = "rooms")
public class RoomModel {
    
    // @Id
    // private ObjectId _id;
    @NotEmpty(message = "Tên phòng không được bỏ trống")
    private String name;
    @NotEmpty(message = "Hình đại diện không được bỏ trống")
    private String avatar;
    @NotEmpty(message = "Giá phòng không được bỏ trống")
    @Size(min = 100000, message = "Giá phòng phải lớn hơn 100k")
    private int price;
    @NotEmpty(message = "Số lượng không được bỏ trống")
    @Size(min = 1, message = "Số lượng lớn hơn 0")
    private int quantity;
    @NotNull(message = "Trạng thái không được bỏ trống")
    private boolean isActive;
}
