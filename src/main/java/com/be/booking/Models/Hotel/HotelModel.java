package com.be.booking.Models.Hotel;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@Document(collection = "hotels")
public class HotelModel {
    
    @Id
    private ObjectId _id;
    @NotEmpty(message = "Tên khách sạn không được bỏ trống")
    private String name;
    @NotEmpty(message = "Địa chỉ không được bỏ trống")
    private String address;
    private int priceMin;
    private int priceMax;
    @NotEmpty(message = "Vĩ độ không được bỏ trống")
    private double lat;
    @NotEmpty(message = "Kinh độ không được bỏ trống")
    private double lon;
    @Size(min = 10, max = 10, message = "Số điện thoại phải 10 ký tự")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Số điện thoại không hợp lệ")
    private String phone;
    @NotEmpty(message = "Thời gian checkin không được bỏ trống")
    private String checkin;
    @NotEmpty(message = "Thời gian checkout không được bỏ trống")
    private String checkout;
    @NotEmpty(message = "Nội dung mô tả không được bỏ trống")
    private String description;
    @NotEmpty(message = "Hình đại diện không được bỏ trống")
    private String avatar;
    private String userId;
    @NotEmpty(message = "Mô hình không được bỏ trống")
    private String typeHotelId;
    private Set<String> images;
    private Set<ObjectId> facilites;
    private List<RoomModel> rooms;
    @NotNull(message = "Trạng thái không được bỏ trống")
    private boolean isActive;

}
