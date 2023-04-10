package com.group.booking.Models.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.UniqueElements;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status")
public class StatusModel {
    
    @Id
    private String id;

    @Column(nullable = false, length = 20)
    @UniqueElements(message = Message.NAME_UNIQUE)
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;
}
