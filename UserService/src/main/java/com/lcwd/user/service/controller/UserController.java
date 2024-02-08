package com.lcwd.user.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	//create user
    //create
//    @PostMapping("/create")
	 @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	System.out.println("omsairam .. createUser invoked :"+user+" ");
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
//	@PostMapping
//	public ResponseEntity<User> createUser(@RequestBody User user)
//	{
//		System.out.println("omsairam .. createUser invoked");
//		User usr= userService.saveUser(user);
//		return ResponseEntity.status(HttpStatus.CREATED).body(usr);
//	}
	//get single user
	@GetMapping("{userId}")
//	public ResponseEntity<Optional<User>> getUser(@PathVariable  String userId){
	public ResponseEntity<User> getUser(@PathVariable  String userId){
		
		System.out.println("omsairam .. getUser:"+userId+" invoked");
		//Optional<User> user=userService.getUser(userId);
		User user=userService.getUser(userId);
		
		return ResponseEntity.ok(user);
	}
	//get all users
	@GetMapping
	public ResponseEntity<List<User>> gerUsers()
	{
		System.out.println("omsairam .. gerUsers invoked");
		return ResponseEntity.ok(userService.getAllUser());
	}
}
