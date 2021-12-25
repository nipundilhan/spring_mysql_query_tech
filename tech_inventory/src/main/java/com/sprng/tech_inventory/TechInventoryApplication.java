package com.sprng.tech_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan("com.sprng")
@SpringBootApplication
public class TechInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechInventoryApplication.class, args);
	}
	
    @Bean
	public RestTemplate getRestTemplate() {
      return new RestTemplate();
	}

}
