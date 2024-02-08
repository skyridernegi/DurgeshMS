package com.lcwd.user.service.services;

import java.util.List;
import java.util.Optional;

import com.lcwd.user.service.entities.User;

public interface UserService {
	//user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

//    Optional<User> getUser(String userId);
    User getUser(String userId);

    //TODO: delete
    //TODO: update
}
