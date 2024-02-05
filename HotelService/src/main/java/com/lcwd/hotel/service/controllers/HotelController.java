package com.lcwd.hotel.service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hotel.service.entites.Hotel;
import com.lcwd.hotel.service.services.impl.HotelServiceImpl;
import com.lcwd.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	HotelService hotelService;
	
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    	System.out.println("omsairam .. createHotel invoked :"+hotel );
    	return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<Optional<Hotel>> getByHotelId(@PathVariable String hotelId) {
    	System.out.println("omsairam .. getByHotelId invoked :"+hotelId );
    	return ResponseEntity.status(HttpStatus.OK).body(hotelService.getByHotelId(hotelId));
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
    	System.out.println("omsairam .. getAll invoked :" );
        return ResponseEntity.ok(hotelService.getAll());
    }
}
