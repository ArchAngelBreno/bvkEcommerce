package com.bvk.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bvk.service.DBService;
import com.bvk.service.EmailService;
import com.bvk.service.MockMailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService service;
	
	@Bean
	public Boolean instantiateDatabase() throws ParseException {
		service.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}
	
}
