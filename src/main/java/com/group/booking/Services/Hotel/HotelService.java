package com.group.booking.Services.Hotel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.booking.Common.Const;
import com.group.booking.Models.Account.UserModel;
import com.group.booking.Models.Addons.Comment;
import com.group.booking.Models.Addons.HotelResponse;
import com.group.booking.Models.Addons.ImagesModel;
import com.group.booking.Models.Addons.PrecentByRoomType;
import com.group.booking.Models.Addons.ResultResponse;
import com.group.booking.Models.Addons.RevenueOn12MonthAgo;
import com.group.booking.Models.Hotel.HotelModel;
import com.group.booking.Models.Hotel.HotelPost;
import com.group.booking.Models.Hotel.HotelResponseModel;
import com.group.booking.Models.Hotel.HotelUpdate;
import com.group.booking.Models.Hotel.ImageHotelModel;
import com.group.booking.Repositories.Account.UserRepository;
import com.group.booking.Repositories.Addons.ImageRepository;
import com.group.booking.Repositories.Hotel.HotelRepository;
import com.group.booking.Repositories.Hotel.ImageHotelRepository;
import com.group.booking.Services.Image.ImageService;
import com.group.booking.Utils.JwtUltil;
import com.group.booking.Utils.ListFilter;
import com.group.booking.Utils.TimeUltil;

@Service
public class HotelService {
    
    @Autowired
    private HotelRepository hotelRepository;
    @PersistenceContext
    private EntityManager db;
    @Autowired
    private ImageHotelRepository imageHotelRepository;
    @Autowired
    private JwtUltil jwtUtil;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private ImageRepository imageRepository;

    public HotelModel foundById(int id) {
        try {
            Optional<HotelModel> foundHotel = hotelRepository.findByIdAndIsActive(id, true);
            return foundHotel.isPresent() ? foundHotel.get() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Page<HotelModel> foundAll(int page, int size) {
        try {
            if(page <= 0) page = 1;
            if(size <= 0) size = 1;

            Pageable pr = PageRequest.of(page - 1, size);
            Page<HotelModel> foundHotel = hotelRepository.findAll(pr);

            return foundHotel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<HotelResponse> findTop8ByRating() {
        try {
            List<HotelResponse> hotels = hotelRepository.findTop8OrderByRating();
            return !hotels.isEmpty() ? hotels : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HotelResponse> findTop8ByNumberRating() {
        try {
            List<HotelResponse> hotels = hotelRepository.findTop8OrderByNumberRating();
            return !hotels.isEmpty() ? hotels : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String checkProvinceAndCheckinAndCheckout(HashMap<String, String> filter) {
        if(filter.get("prv") == null) {
            return "Hãy chọn 1 thành phố";
        } else if(filter.get("ci") == null && filter.get("co").length() == 10) {
            return "Hãy chọn ngày nhận phòng";
        } else if(filter.get("co") == null && filter.get("co").length() == 10) {
            return "Hãy chọn ngày trả phòng";
        } else if(filter.get("co").compareTo(filter.get("ci")) < 1) {
            return "Bạn phải ở tối thiểu 1 ngày";
        }

        return "OK";
    }

    public String generateQuery(HashMap<String, String> filter) {
        String where = " WHERE province_id = '" + filter.get("prv") + "' ";
        for (Map.Entry<String, String> map : filter.entrySet()) {
            String valueOfKey = ListFilter.keys().get(map.getKey());
            // if key in filter exist in backend
            if(valueOfKey != null) {
                List<String> myList = new ArrayList<String>(Arrays.asList(map.getValue().split(",")));
                // if size value of key == 1
                if(myList.size() == 1) {
                    String valueOfValue = ListFilter.values().get(myList.get(0));
                    // if value of value axist in backend
                    if(valueOfValue != null) where += " and " + valueOfValue + " ";
                }
                // if size value of key > 1 
                else if(myList.size() > 1) {
                    String whereAnd = " and (";
                    for (String string : myList) {
                        String valueOfValue = ListFilter.values().get(string);
                        // if > 1 then condition 'or'
                        if(valueOfValue != null && whereAnd.length() == 6) whereAnd += valueOfValue;
                        else if(valueOfValue != null && whereAnd.length() != 6) whereAnd += " or " + valueOfValue ;
                    }
                    whereAnd += ") ";
                    if(whereAnd.length() > 8) where += whereAnd;
                }
            }
        }
        where += " and is_active = true ";
        return where;
    }

    public ResultResponse getListHotelsByFilter(HashMap<String, String> filter) {
        try {
            int page = 1;
            int size = 10;
            String sort = "";
            if(filter.get("page") != null) {
                page = Integer.valueOf(filter.get("page"));
            }
            if(filter.get("size") != null) {
                size = Integer.valueOf(filter.get("size"));
            }
            if(filter.get("sort") != null) {
                if(filter.get("sort") == "ASC") sort = "ORDER BY price_min ASC";
                else sort = "ORDER BY price_min DESC";
            }

            String query = "SELECT "+
                            "id, "+
                            "name, "+
                            "address, "+
                            "phone, "+
                            "checkin, "+
                            "checkout, "+
                            "lat, "+
                            "lon, "+
                            "price_min, "+
                            "price_max, "+
                            "avatar, "+
                            "rating, "+
                            "num_rating, "+
                            "(SELECT name from hotel_types WHERE id = hotels.hotel_type_id) hotel_type_name, " +
                            "(SELECT name from provinces WHERE id = hotels.province_id) province_name " +
                            "FROM hotels " + generateQuery(filter) + sort + " LIMIT " + size + " OFFSET " + (page-1);
            String queryCount = "SELECT COUNT(id) FROM hotels " + generateQuery(filter);
            // return db.createNativeQuery(query, HotelResponseModel.class).getResultList();
            int totalElements = Integer.valueOf(db.createNativeQuery(queryCount).getResultList().get(0).toString());

            return totalElements != 0 ?
                new ResultResponse(db.createNativeQuery(query, HotelResponseModel.class).getResultList(), totalElements, (int) Math.ceil(1.0*totalElements/size), page, size)
                :
                new ResultResponse("", totalElements, (int) Math.ceil(1.0*totalElements/size), page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ImageHotelModel> findAllImageByHotelId(int hotelId) {
        try {
            List<ImageHotelModel> data = imageHotelRepository.findByHotelId(hotelId);
            return data != null ? data : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultResponse findCommentByHotelId(int hotelId, int page, int size) {
        try {

            String query =  
                "SELECT " + 
                    "ho.id, " +
                    "ho.modify_time, "+
                    "ho.rating, "+
                    "ho.comment, "+
                    "u.avatar, "+
                    "u.gender, "+
                    "CONCAT(u.firstname, ' ', u.lastname) name "+
                "FROM (SELECT * FROM hotel_orders WHERE hotels_id = " + hotelId + " and rating IS NOT null) ho "+
                "INNER JOIN users u " +
                "ON u.id = ho.users_id ORDER BY ho.modify_time LIMIT " + size + " OFFSET " + (page-1);
            String queryCount = "SELECT COUNT(id) FROM hotel_orders WHERE hotels_id = " + hotelId + " and rating IS NOT null";
            int totalElements = Integer.valueOf(db.createNativeQuery(queryCount).getResultList().get(0).toString());
            return totalElements != 0 ?
                new ResultResponse(db.createNativeQuery(query, Comment.class).getResultList(), totalElements, (int) Math.ceil(1.0*totalElements/size), page, size)
                :
                new ResultResponse("", totalElements, (int) Math.ceil(1.0*totalElements/size), page, size);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<RevenueOn12MonthAgo> getRevenueOn12MonthAgo(String authorization) {
        try {
            String userId = jwtUtil.validateAndGetSubject(authorization);
            if(!userId.equals("")) {
                HotelModel foundHotel = foundByUserId(Integer.valueOf(userId));
                if(foundHotel != null) {
                    String query =  
                        "SELECT "+
                            "ROW_NUMBER() OVER(ORDER BY ho.month ASC) ind, "+
                            "ho.month, SUM(hod.price * hod.quantity * DATEDIFF(ho.checkout, ho.checkin)) total "+
                        "FROM hotel_order_details hod "+
                        "INNER JOIN ( " +
                            "SELECT id, checkin, checkout, CONCAT(month(checkin),'-',year(checkin)) month "+
                            "FROM booking.hotel_orders " +
                            "WHERE hotels_id = "+ foundHotel.getId() + " AND status_id = '" + Const.COMPLETE + "'"+ 
                                " AND checkin >= '"+ TimeUltil.getCurrentMonthAgoYear() +"' AND checkin <= '" + TimeUltil.getCurrentMonth() + "'"+
                        ") ho "+
                        "ON hod.hotel_orders_id = ho.id "+
                        "GROUP BY ho.month";
                    // "-- hotels_id = 1 and status_id = 'DAHOANTHANH'"
                    return db.createNativeQuery(query, RevenueOn12MonthAgo.class).getResultList();
                }
            }
        } catch (Exception e) {}
        return null;
    }

    public List<PrecentByRoomType> getPrecentByRoomType(String authorization) {
        try {
            String userId = jwtUtil.validateAndGetSubject(authorization);
            if(!userId.equals("")) {
                HotelModel foundHotel = foundByUserId(Integer.valueOf(userId));
                if(foundHotel != null) {
                    String query =  
                    "SELECT "+
                        "hod.room_type_id id, "+
                        "(SELECT name from room_type rt WHERE rt.id = hod.room_type_id) name, "+
                        "SUM(hod.quantity) quantity "+
                    "FROM hotel_order_details hod "+
                    "INNER JOIN ( SELECT id "+
                                "FROM hotel_orders  WHERE hotels_id = "+foundHotel.getId()+") ho" +
                    " ON ho.id = hod.hotel_orders_id "+
                    "GROUP BY room_type_id";
                    // "-- hotels_id = 1 and status_id = 'DAHOANTHANH'"
                    return db.createNativeQuery(query, PrecentByRoomType.class).getResultList();
                }
            }
        } catch (Exception e) {}
        return null;
    }

    public String getDescription(String authorization) {
        HotelModel foundHotel = foundByAuthorization(authorization);
        if(foundHotel != null) {
            return foundHotel.getDescription();
        }
        return null;
    }

    public String updateDescription(String description, String authorization) {
        try {
            if(description.length() < 10) return "Nội dung mô tả phải lớn hơn 10 ký tự";
            HotelModel foundHotel = foundByAuthorization(authorization);
            if(foundHotel != null) {
                hotelRepository.updateByDescription(description, foundHotel.getId());
                return "OK";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Quyền không hợp lệ";
    }

    public HotelUpdate updateHotel(HotelUpdate hotel, String authorization) {
        try {
            HotelModel foundHotel = foundByAuthorization(authorization);
            if(foundHotel != null) {
                foundHotel.setName(hotel.getName());
                foundHotel.setAddress(hotel.getAddress());
                foundHotel.setPhone(hotel.getPhone());

                foundHotel.setCheckin(hotel.getCheckin());
                foundHotel.setCheckout(hotel.getCheckout());
                foundHotel.setLat(hotel.getLon());
                foundHotel.setLat(hotel.getLat());

                if(foundHotel.getAvatar().length() != hotel.getAvatar().length()) {
                    String avatar = imageService.putImage(Const.HOST+foundHotel.getAvatar(), Base64.getDecoder().decode(hotel.getAvatar()), authorization);
                    if(!avatar.equals("")) {
                        foundHotel.setAvatar(avatar);
                        hotel.setAvatar(avatar);
                    }
                }
                
                hotelRepository.updateHotel(
                    foundHotel.getName(), 
                    foundHotel.getAddress(), 
                    foundHotel.getPhone(), 
                    foundHotel.getCheckin(), 
                    foundHotel.getCheckout(), 
                    foundHotel.getLat(), 
                    foundHotel.getLon(), 
                    foundHotel.getAvatar(), 
                    foundHotel.getId());
                
                return hotel;
            }
            System.out.println(hotel.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean changeActive(int hotelId, String authorization) {
        try {
            String userId = jwtUtil.validateAndGetSubject(authorization);
            Optional<HotelModel> foundHotel = hotelRepository.findById(hotelId);
            if(userId != "" && foundHotel.isPresent()) {
                Optional<UserModel> foundUser = userRepository.findById(foundHotel.get().getUserId());
                if(foundUser.isPresent()) {
                    foundUser.get().setActive(!foundHotel.get().isActive());
                    userRepository.save(foundUser.get());
                    hotelRepository.updateActive(hotelId, !foundHotel.get().isActive());
                    return true;
                }
            }
        } catch (Exception e) {}
        return false;
    }

    @Transactional
    public String saveHotel(HotelPost model, String authorization) {
        try {
            String userId = jwtUtil.validateAndGetSubject(authorization);
            if(userId != "") {
                Optional<UserModel> foundUser = userRepository.findById(Integer.valueOf(userId));
                if(foundUser.isPresent() && foundUser.get().getRoleId().equals("ADMIN")) {
                    UserModel user = new UserModel();
                    byte[] i = new byte[0];

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    user.setUsername(model.getUsername());
                    user.setPassword(encoder.encode(model.getPassword()));
                    user.setFirstname(model.getFirstname());
                    user.setLastname(model.getLastname());
                    user.setPhone(model.getPhone());
                    user.setEmail(model.getEmail());
                    dateFormat.parse(model.getBirthday());
                    user.setBirthday(dateFormat.parse(model.getBirthday()));
                    user.setGender(model.getGender());
                    user.setActive(true);
                    user.setAvatar("");
                    user.setRoleId("HOTEL");

                    user = userRepository.save(user);

                    // avatar user
                    ImagesModel img = new ImagesModel();
                    img.setUserId(user.getId());
                    img.setData(i);
                    ImagesModel imageUser = imageRepository.save(img);
                    System.err.println("user: " + imageUser.getId());
                    user.setAvatar("/api/v1/images/"+imageUser.getId());
                    userRepository.save(user);
                    // avatar hotel

                    ImagesModel img2 = new ImagesModel();
                    img2.setUserId(user.getId());
                    img2.setData(i);
                    ImagesModel imageHotel = imageRepository.save(img2);
                    System.err.println("hotel: " + imageHotel.getId());
                    
                    hotelRepository.insertHotel(
                        model.getName(), 
                        model.getAddress(), 
                        model.getPhoneHotel(), 
                        model.getCheckin(), 
                        model.getCheckout(), 
                        "/api/v1/images/"+imageHotel.getId(), 
                        model.getProvince(), 
                        model.getHotelType(), 
                        user.getId(), 
                        true, "");

                    return "true";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        return "false";
    }

    public HotelModel foundByUserId(int userId) {
        Optional<HotelModel> foundhotel = hotelRepository.findByUserIdAndIsActive( userId, true);
        return foundhotel.isPresent() ? foundhotel.get() : null;
    }

    public HotelModel foundByAuthorization(String authorization) {
        String userId = jwtUtil.validateAndGetSubject(authorization);
        if(!userId.equals("")) {
            return foundByUserId(Integer.valueOf(userId));
        }
        return null;
    }
}
