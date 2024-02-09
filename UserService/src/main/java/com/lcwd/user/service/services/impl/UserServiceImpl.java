package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
//import com.sun.org.slf4j.internal.LoggerFactory;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepo;
	
	Logger logger= org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		//autogenerating user-id by java random system
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();//this will return without hotel details, if we want to get hotel details as well then we need to implement the logic which we used in getUser() to call Ratingservice ang get Hotel details
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		Optional<User> user=  userRepo.findById(userId);
		
		//getting user given ratings from RatingService
		//localhost:8083/ratings/users/4836f799-a676-47f6-9874-e90e22849e82
		Rating[] userRating=restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.get().getUserId()	, Rating[].class);
		User returnUser=user.get();
		
		//getting all the Hotel details inn Rating and then need to set into Rating
		//for this we need to iterate Rating and call to HotelService to get hotel Details by passing hotelId
		
		//->http://localhost:8082/hotels/cc53390c-bf53-4af9-9fc7-7b5418222b39
		List<Rating> ratingList =
		Arrays.stream(userRating).map(rating->{
			ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel=hotelResponseEntity.getBody();
			logger.debug("FetchedHotelFromAPIStatus:"+hotelResponseEntity.getStatusCodeValue());
			logger.debug("FetchedHotelFromAPI:"+hotel);
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		returnUser.setRatings(ratingList);
//		returnUser.setRatings(Arrays.asList(userRating));//this was without Hotel Details, but above code written providing Hotel Details as well
		System.out.println("userRating_omsairam:@@@@---->>>>"+userRating);
		return returnUser;
	}

}
