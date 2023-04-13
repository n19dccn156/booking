package com.group.booking.Models.Order;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.booking.Common.Message;
import com.group.booking.Models.Addons.OrderRequest;
import com.group.booking.Utils.TimeUltil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "hotel_orders")
public class HotelOrderModel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotEmpty(message = Message.CHECKIN_NOT_EMP)
    private String checkin;

    @Column(nullable = false)
    @NotEmpty(message = Message.CHECKOUT_NOT_EMP)
    private String checkout;

    @Column(name = "create_at", nullable = false, length = 20)
    private String createdAt;

    @Column(name = "modify_time", nullable = false, length = 20)
    private String modifyTime;
    
    @Column(nullable = false, length = 50)
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;

    @Column(nullable = false, length = 10)
    @NotEmpty(message = Message.PHONE_NOT_EMP)
    private String phone;

    private Integer rating;
    
    private String comment;

    @Column(name = "users_id", nullable = false)
    private int userId;

    @Column(name = "hotels_id", nullable = false)
    private int hotelId;

    @Column(name = "status_id", nullable = false)
    private String statusId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StatusModel status;

    @JsonManagedReference
    @OneToMany(mappedBy = "hotelOrder", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<HotelOrderDetailModel> orderDetails;
    
    public HotelOrderModel(OrderRequest order, String status) {
        this.statusId = status;
        this.name = order.getName();
        this.phone = order.getPhone();
        this.userId = order.getUserId();
        this.hotelId = order.getHotelId();
        this.checkin = order.getCheckin();
        this.checkout = order.getCheckout();
        this.createdAt = TimeUltil.getCurrentTimeStamp();
        this.modifyTime = TimeUltil.getCurrentTimeStamp();
    }
}
