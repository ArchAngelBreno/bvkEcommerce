package com.bvk.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bvk.domain.Customer;
import com.bvk.repository.CustomerRepository;
import com.bvk.service.exception.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	
	public void sendNewPassword(String email) {
		Customer cus = customerRepository.findByEmail(email);
		if (cus == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		
		String newPass = newPassword();
		cus.setPassword(encoder.encode(newPass));
		customerRepository.save(cus);
		emailService.sendNewPasswordEmail(cus,newPass);
	}


	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}


	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) {
			return (char) (random.nextInt(10)+48);
		}else if (opt==1) {
			return (char) (random.nextInt(10)+65);
		}else {
			return (char) (random.nextInt(10)+97);
		}
	}
}
