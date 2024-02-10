package com.lcwd.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entities.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
//http://HOTEL-SERVICE/hotels/"+rating.getHotelId()
	@GetMapping("/hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
