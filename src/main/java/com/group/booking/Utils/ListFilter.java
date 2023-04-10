package com.group.booking.Utils;

import java.util.HashMap;

public class ListFilter {
    
    public static final HashMap<String, String> values() {
        HashMap<String, String> map = new HashMap<>();
        // type hotel
        map.put("1", "hotel_type_id = 'HOTEL'");
        map.put("2", "hotel_type_id = 'HOMESTAY'");
        map.put("3", "hotel_type_id = 'VILLA'");
        // rating
        map.put("11", "(rating < 1.5)");
        map.put("12", "(rating >= 1.5 and rating < 2.5)");
        map.put("13", "(rating >= 2.5 and rating < 3.5)");
        map.put("14", "(rating >= 3.5 and rating < 4.5)");
        map.put("15", "(rating >= 4.5 and rating <= 5)");
        // price
        map.put("21", "(price_min >=0 and price_min <= 500000)");
        map.put("22", "(price_min >= 500000 and price_min <= 2000000)");
        map.put("23", "(price_min >= 2000000 and price_min <= 5000000)");
        map.put("24", "(price_min >= 5000000)");  

        return map;
    }

    public static final HashMap<String, String> keys() {
        HashMap<String, String> map = new HashMap<>();
        map.put("ht", "hotel_type_id");
        map.put("rt", "rating");
        map.put("pri", "price");
        map.put("prv", "province");
        map.put("ci", "checkin");
        map.put("co", "checkout");

        return map;
    }
}
