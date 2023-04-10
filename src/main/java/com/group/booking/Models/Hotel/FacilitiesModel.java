package com.group.booking.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "facilities")
public class FacilitiesModel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    @UniqueElements(message = Message.NAME_UNIQUE)
    private String name;
    
    private String iconWeb;
    private String iconMob;
}
