package com.group.booking.Services.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Models.Hotel.ProvinceModel;
import com.group.booking.Models.Hotel.ProvincePostModel;
import com.group.booking.Repositories.Hotel.ProvinceRepository;
import com.group.booking.Services.Image.ImageService;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ImageService imageService;
    
    public List<ProvinceModel> getAllProvinces() {
        try {
            List<ProvinceModel> provinces = provinceRepository.findAll();
            return provinces;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProvinceModel getById(String id) {
        try {
            Optional<ProvinceModel> foundProvince = provinceRepository.findById(id);
            return foundProvince.isPresent() ? foundProvince.get() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProvinceModel getByName(String name) {
        try {
            Optional<ProvinceModel> foundProvince = provinceRepository.findByName(name);
            return foundProvince.isPresent() ? foundProvince.get() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProvinceModel postProvince(ProvincePostModel province, String authorization) {
        try {
            if(!imageService.checkSizeFile(province.getAvatar())) return new ProvinceModel("", Message.SIZE_VALIDATE, "");
            province.preProcessing();
            List<ProvinceModel> foundProvince = provinceRepository.findByIdOrName(province.getId(), province.getName());
            if(foundProvince.isEmpty()) {
                String avatar = imageService.postImage(province.getAvatar(), authorization);
                if(avatar.length() != 0) {
                    ProvinceModel provinceModel = new ProvinceModel(province, avatar);
                    provinceRepository.save(provinceModel);
                    return provinceModel;
                }
            }
            return new ProvinceModel("", Message.ID_NAME_UNIQUE, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProvinceModel putProvince(ProvincePostModel province, String authorization)  {
        try {
            if(!imageService.checkSizeFile(province.getAvatar())) return new ProvinceModel("", Message.SIZE_VALIDATE, "");
            List<ProvinceModel> foundProvince = provinceRepository.findByIdOrName(province.getId(), province.getName());
            if(foundProvince.size() == 1 && foundProvince.get(0).getId().equals(province.getId())) {
                String avatar = imageService.putImageForManager(Const.HOST+foundProvince.get(0).getAvatar(), province.getAvatar().getBytes(), authorization);
                
                if(avatar.length() != 0) {
                    ProvinceModel model = new ProvinceModel(province, avatar);
                    provinceRepository.save(model);
                    return model;
                }
            }
            return new ProvinceModel("", Message.ID_NAME_UNIQUE, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

