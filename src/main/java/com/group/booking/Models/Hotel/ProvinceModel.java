package com.group.booking.Models.Hotel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provinces")
public class ProvinceModel {
    
    @Id
    private String id;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;

    private String avatar;

    public ProvinceModel(ProvincePostModel model, String avatar) {
        this.id = model.getId();
        this.name = model.getName();
        this.avatar = avatar;
    }
}
