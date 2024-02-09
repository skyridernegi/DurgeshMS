omsairam\n this directory containing my MS practice code with all the required modules to design a Microservice based project


#09-Feb-2024: code updated for UserService "localhost:8081/users/23e7178f-9e53-41d9-b5a8-d770399e8f03", 
	1-now this will call internally 2 api to get User's HotelRating and Hotel details
	2-code is still using RestTemplate and full Service URL rather name of the sevice , which we will change in next commit

#Branch: serviceCallByName_LoadBalanceAnnotation:
Here we are replacing service URL/port by the EurekaServer registered service-name:
#1->
			//ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
#			//now we are using directly ServiceName to avoid hardcoded URL/PORT
#			//and to let restTemplate(client) to know about service-name from EurekaServer we have to use @LoadBalanced where we creating bean of RestTemplate
			ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

#2->

		//Rating[] userRating=restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.get().getUserId()	, Rating[].class);

#		//now we are using directly ServiceName to avoid hardcoded URL/PORT
#		//and to let restTemplate(client) to know about service-name from EurekaServer we have to use @LoadBalanced where we creating bean of RestTemplate
		Rating[] userRating=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.get().getUserId()	, Rating[].class);

