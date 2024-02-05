package com.lcwd.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	@Autowired
	RatingService ratingService;
	//create
	 @PostMapping
//	@PostMapping("/create")
	 public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		 System.out.println("omsairam create invoked:"+rating);
		 return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	 }
	//getAllRatings
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings()
	{
		 System.out.println("omsairam getAllRatings invoked:");
		return ResponseEntity.ok(ratingService.getRatings());
	}
	//get all of user
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
    	 System.out.println("omsairam getRatingsByUserId invoked:"+userId);
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
    //get all of hotels
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
    	 System.out.println("omsairam getRatingsByHotelId invoked:"+hotelId);
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
