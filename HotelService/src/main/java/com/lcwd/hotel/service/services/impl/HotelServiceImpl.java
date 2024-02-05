package com.lcwd.hotel.service.services.impl;

import com.lcwd.hotel.service.entites.Hotel;
import com.lcwd.hotel.service.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;
import com.lcwd.hotel.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class HotelServiceImpl  implements HotelService   {
	@Autowired
    private HotelRepository hotelRepository;
	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
	}
	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}
	@Override
	public Optional<Hotel> getByHotelId(String id) {
		// TODO Auto-generated method stub
		return hotelRepository.findById(id);
//		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id="+id+"not found !!"));
	}
}
