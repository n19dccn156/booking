package com.group.booking.Controllers.Image;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.booking.Interfaces.Image.ImageImpl;
import com.group.booking.Models.Addons.ImagesModel;
import com.group.booking.Services.Image.ImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/images")
@Api(tags = "Image")
public class ImageController implements ImageImpl {

    @Autowired
    private ImageService imageService;

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "get image [ALL]", consumes = "application/json")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) {
        ImagesModel foundImg = imageService.getImage(id);
        return foundImg != null ?
            ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(
                foundImg.getData()
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                "".getBytes()
            );
    }

    @Override
    @PostMapping("")
    @ApiOperation(value = "post image [ALL]", consumes = "application/json")
    public ResponseEntity<ImagesModel> postImage(ImagesModel image, HttpServletRequest request) {
        
        
        return null;
    }

    @Override
    @PutMapping("")
    @ApiOperation(value = "put image [ALL]", consumes = "application/json")
    public ResponseEntity<ImagesModel> putImage(ImagesModel model, HttpServletRequest request) {
        
        imageService.getIdByUrl("null");

        return null;
    }

}
