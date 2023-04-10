package com.group.booking.Interfaces.Image;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.group.booking.Models.Addons.ImagesModel;

public interface ImageImpl {
    
    public ResponseEntity<byte[]> getImage(int id);
    public ResponseEntity<ImagesModel> putImage(ImagesModel model, HttpServletRequest request);
    public ResponseEntity<ImagesModel> postImage(ImagesModel image, HttpServletRequest request);

}
