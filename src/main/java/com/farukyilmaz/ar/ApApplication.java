package com.farukyilmaz.ar;

import com.farukyilmaz.ar.controllers.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApApplication {

	static RestController restController;
	public static void main(String[] args) {
		SpringApplication.run(ApApplication.class, args);
	}
}
