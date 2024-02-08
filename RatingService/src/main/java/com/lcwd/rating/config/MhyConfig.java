package com.lcwd.rating.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Configurable
public class MhyConfig {

	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
}
