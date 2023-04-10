package com.group.booking.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;

import com.group.booking.Common.Message;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Entity
@Table(name = "hotel_types")
public class HotelTypeModel {
    
    @Id
    private String id;
    
    @Column(nullable = false, length = 50)
    @UniqueElements(message = Message.NAME_UNIQUE)
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;
}
