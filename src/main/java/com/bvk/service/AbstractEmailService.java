package com.bvk.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bvk.domain.Order;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine template;
	
//	@Autowired
//	private JavaMailSender mailSender;
	
	@Override
	public void sendOrderConfirmation(Order order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(order);
		sendEmail(sm);
	}
	
	@Override
	public void sendOrderConfirmationEmailHtml(Order order) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromOrder(order);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmation(order);
		}
	}

	protected MimeMessage prepareMimeMessageFromOrder(Order order) throws MessagingException {
		MimeMessage mm = null; /*mailSender.createMimeMessage();*/
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setTo(order.getCustomer().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: "+order.getId());
		mmh.setText(htmlFromTemplateOrder(order),true);
		return mm;
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getCustomer().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Codigo: "+order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}
	
	
	protected String htmlFromTemplateOrder(Order order) {
		Context context = new Context();
		context.setVariable("order", order);
		return template.process("email/confirmOrder", context);
	}
}
