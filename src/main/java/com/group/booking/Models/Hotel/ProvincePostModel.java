package com.group.booking.Models.Hotel;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.group.booking.Common.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvincePostModel {
    
    @Pattern(regexp = "^[a-zA-Z]{2, 20}$", message = Message.LASTNAME_VALIDATE)
    @NotEmpty(message = Message.ID_NOT_EMPTY)
    private String id;

    @Pattern(regexp = "^[a-zA-Z]{5, 50}$", message = Message.LASTNAME_VALIDATE)
    @NotEmpty(message = Message.NAME_NOT_EMP)
    private String name;

    @NotEmpty(message = Message.AVATAR_NOT_EMP)
    @Lob
    private MultipartFile avatar;

    public void preProcessing() {
        this.id = id.trim().toUpperCase();
        this.name = name.trim();
    }
}
