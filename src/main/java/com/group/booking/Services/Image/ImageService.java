package com.group.booking.Services.Image;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.group.booking.Common.Const;
import com.group.booking.Models.Account.UserModel;
import com.group.booking.Models.Addons.ImagesModel;
import com.group.booking.Repositories.Addons.ImageRepository;
import com.group.booking.Services.Account.UserService;
import com.group.booking.Utils.JwtUltil;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private JwtUltil jwtUltil;
    @Autowired
    private UserService userService;

    public int getIdByUrl(String urlImage) {
        try {
            return Integer.valueOf(urlImage.split("/")[6]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ImagesModel getImage(int id) {
        try {
            Optional<ImagesModel> foundImg = imageRepository.findById(id);
            if(foundImg.isPresent()) {
                return foundImg.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String putImage(String urlImg, byte[] image, String authorization) {
        try {
            int idImg = getIdByUrl(urlImg);
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(idImg != 0 && userId.length() != 0) {
                UserModel foundUser = userService.foundUserById(Integer.valueOf(userId));
                ImagesModel foundImage = findById(idImg);

                if(foundUser != null && foundImage != null && foundImage.getUserId() == foundImage.getUserId()) {
                    foundImage.setData(image);
                    imageRepository.save(foundImage);
                    return "/api/v1/images/"+idImg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String putImageForManager(String urlImg, byte[] image, String authorization) {
        try {
            int idImg = getIdByUrl(urlImg);
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(idImg != 0 && userId.length() > 0) {
                UserModel foundUser = userService.foundUserById(Integer.valueOf(userId));
                ImagesModel foundImage = findById(idImg);
                if(foundUser != null && foundImage != null && (foundUser.getRoleId().equals(Const.ROLE_EMPLOYEE) || foundUser.getRoleId().equals(Const.ROLE_ADMIN))) {
                    foundImage.setData(image);
                    imageRepository.save(foundImage);
                    return "/api/v1/images/"+idImg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String postImage(MultipartFile img, String authorization) {
        try {
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(userId.length() != 0) {
                UserModel foundUser = userService.foundUserById(Integer.valueOf(userId));
                if(foundUser != null) {
                    ImagesModel image = new ImagesModel();
                    image.setData(img.getBytes());
                    image.setUserId(foundUser.getId());
                    ImagesModel imgSave = imageRepository.save(image);
                    return "/api/v1/images/"+imgSave.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String postImage(byte[] img, String authorization) {
        try {
            String userId = jwtUltil.validateAndGetSubject(authorization);
            if(userId.length() != 0) {
                UserModel foundUser = userService.foundUserById(Integer.valueOf(userId));
                if(foundUser != null) {
                    ImagesModel image = new ImagesModel();
                    image.setData(img);
                    image.setUserId(foundUser.getId());
                    ImagesModel imgSave = imageRepository.save(image);
                    return "/api/v1/images/"+imgSave.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public ImagesModel findById(int idImg) {
        Optional<ImagesModel> foundImg = imageRepository.findById(idImg);
        return foundImg.isPresent() ? foundImg.get() : null;
    }

    public boolean checkSizeFile(MultipartFile file) {
        if(file.getSize() < Const.MAX_SIZE) {
            return true;
        } else {
            return false;
        }
    }
}
