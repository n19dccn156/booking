package com.group.booking;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group.booking.Models.Order.OrderGroupByStatus;
import com.group.booking.Services.Order.HotelOrderService;

@SpringBootTest
class BookingApplicationTests {

	@Test
	void contextLoads() {
		HotelOrderService order = new HotelOrderService();
		List<OrderGroupByStatus> foundOrders = order.getByHotelIdAndGroupByHotelId("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNjgxMTI5OTMwLCJleHAiOjE2ODEzODkxMTB9.XGVzFC2k6oVijQ9Wn8kwEUluGp_s3gQB0ZU9sBxbqTWFV53X7GTQLU3oOz7fiKIxCH-nz8mbTg4qkJXJ7pPi0A");
		System.out.println(foundOrders.size());
		System.out.println(foundOrders.get(0).getName());
	}

}
