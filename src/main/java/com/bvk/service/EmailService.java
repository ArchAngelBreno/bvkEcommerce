package com.bvk.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bvk.domain.Customer;
import com.bvk.domain.Order;

@Service
public interface EmailService {

	void sendOrderConfirmation(Order order);
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationEmailHtml(Order order);
	void sendHtmlEmail(MimeMessage msg);
	void sendNewPasswordEmail(Customer cus, String newPass);
	
}
