package com.group.booking.Services.Hotel;

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
import org.springframework.stereotype.Service;

import com.group.booking.Common.Const;
import com.group.booking.Models.Addons.Comment;
import com.group.booking.Models.Addons.HotelResponse;
import com.group.booking.Models.Addons.PrecentByRoomType;
import com.group.booking.Models.Addons.ResultResponse;
import com.group.booking.Models.Addons.RevenueOn12MonthAgo;
import com.group.booking.Models.Hotel.HotelModel;
import com.group.booking.Models.Hotel.HotelResponseModel;
import com.group.booking.Models.Hotel.HotelUpdate;
import com.group.booking.Models.Hotel.ImageHotelModel;
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

    public HotelModel foundById(int id) {
        try {
            Optional<HotelModel> foundHotel = hotelRepository.findByIdAndIsActive(id, true);
            return foundHotel.isPresent() ? foundHotel.get() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            List<ImageHotelModel> data = imageHotelRepository.findAllByHotelId(hotelId);
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
                    "ho.modify_time modifyTime, "+
                    "ho.rating, "+
                    "ho.comment, "+
                    "u.avatar, "+
                    "u.gender, "+
                    "CONCAT(u.firstname, ' ', u.lastname) name "+
                "FROM (SELECT * FROM hotel_orders WHERE hotels_id = " + hotelId + " and rating != null) ho "+
                "INNER JOIN users u " +
                "ON u.id = ho.users_id ORDER BY ho.modify_time LIMIT " + size + " OFFSET " + (page-1);
            String queryCount = "SELECT COUNT(id) FROM hotel_orders WHERE hotels_id = " + hotelId + " and rating != null";
            int totalElements = Integer.valueOf(db.createNativeQuery(queryCount).getResultList().get(0).toString());
            return totalElements != 0 ?
                new ResultResponse(db.createNativeQuery(query, Comment.class).getResultList(), totalElements, (int) Math.ceil(1.0*totalElements/size), page, size)
                :
                new ResultResponse("", totalElements, (int) Math.ceil(1.0*totalElements/size), page, size);
        } catch (Exception e) {
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

    public List<PrecentByRoomType> getPrecentByRoomType() {
        try {
            String query =  
                "SELECT "+
                    "hod.room_type_id id, "+
                    "(SELECT name from room_type rt WHERE rt.id = hod.room_type_id) name, "+
                    "SUM(hod.quantity) quantity "+
                "FROM hotel_order_details hod "+
                "INNER JOIN ( SELECT id "+
                            "FROM hotel_orders ) ho "+
                "ON ho.id = hod.hotel_orders_id "+
                "GROUP BY room_type_id";
                // "-- hotels_id = 1 and status_id = 'DAHOANTHANH'"
            return db.createNativeQuery(query, PrecentByRoomType.class).getResultList();
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
