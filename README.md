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



# ############################ Branch openFeign_Feignclient-usage changes details on Date 10-Feb-2024 2049 #################
//in this branch we implemented Feign client in User-Service to call other microservices
// there are following 3 steps which we need to do
#- we need to add dependency on pom.xml , for this we can open springinitializer site and add OpenFeign and then explor then copy the depency and paste in UserService pom.xml
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>

#-After this we need to open main Application
package com.lcwd.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean
	@LoadBalanced
	RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
}
#- after this we need to create an Interface for declaring Methods same as per the required api to invoke instead calling directly api

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
#	#Note:this method implementation will be provide at runtime by FeignClient from Hotel-Service

#- after this we can call above Interface method directly by using annotation in UserService class
@Service
public class UserServiceImpl implements UserService{

	//feignClient Service Interface
	@Autowired
	HotelService hotelServiceFC;


#//			ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
#//			Hotel hotel=hotelResponseEntity.getBody();
#//			logger.debug("FetchedHotelFromAPIStatus:"+hotelResponseEntity.getStatusCodeValue());
			
#			//now above 2 78,79 line will be replaced by direct calling of feignClient
			Hotel hotel= hotelServiceFC.getHotel(rating.getHotelId());


