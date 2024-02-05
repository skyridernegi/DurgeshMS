package com.lcwd.hotel.services;


import java.util.List;
import java.util.Optional;

import com.lcwd.hotel.service.entites.Hotel;

public interface HotelService {

    //create
    Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Optional<Hotel> getByHotelId(String id);

}
