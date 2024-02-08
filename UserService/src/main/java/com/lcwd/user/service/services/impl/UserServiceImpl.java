package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserRepository userRepo;
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
		return userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		Optional<User> user=  userRepo.findById(userId);
		
		//getting user given ratings from RatingService
		//localhost:8083/ratings/users/4836f799-a676-47f6-9874-e90e22849e82
		Rating[] userRating=restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.get().getUserId()	, Rating[].class);
		User returnUser=user.get();
		returnUser.setRatings(Arrays.asList(userRating));
		System.out.println("userRating_omsairam:@@@@---->>>>"+userRating);
		return returnUser;
	}

}
