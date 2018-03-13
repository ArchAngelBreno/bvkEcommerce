package com.bvk.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bvk.domain.Customer;
import com.bvk.domain.Order;
import com.bvk.domain.OrderItem;
import com.bvk.domain.PaymentSlip;
import com.bvk.enumerator.PaymentStatus;
import com.bvk.repository.CustomerRepository;
import com.bvk.repository.OrderItemRepository;
import com.bvk.repository.OrderRepository;
import com.bvk.repository.PaymentRepository;
import com.bvk.repository.ProductRepository;
import com.bvk.security.UserSS;
import com.bvk.service.exception.AuthorizationException;
import com.bvk.service.exception.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SlipService slipService;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	public Order findById(Long id) {
		Order order = orderRepository.findOne(id);
		if (order == null) {
			throw new ObjectNotFoundException("Não foi encontrado nenhum id: " + id + ", verifique com a gerencia");
		}
		return order;
	}

	public Order insert(Order order) {
		order.setId(null);
		order.setOrderDate(new Date());
		order.setCustomer(customerRepository.findOne(order.getCustomer().getId()));
		order.getPayment().setPaymentStatus(PaymentStatus.PENDENTE);
		order.getPayment().setOrder(order);
		if (order.getPayment() instanceof PaymentSlip) {
			PaymentSlip slip = (PaymentSlip) order.getPayment();
			slipService.completePaymentWithSlip(slip,order.getOrderDate());
		}
		
		order = orderRepository.save(order);
		paymentRepository.save(order.getPayment());
		for (OrderItem ip : order.getItems()) {
			ip.setDesconto(0.0);
			ip.setProduct(productRepository.findOne(ip.getProduct().getId()));
			ip.setPreco(ip.getProduct().getPreco());
			ip.setOrder(order);
		}
		orderItemRepository.save(order.getItems());
		emailService.sendOrderConfirmationEmailHtml(order);
		return order;
	}
	
	
	
	public Page<Order> findByPage(Integer page, Integer linePerPage,String orderBy,String direction){
		UserSS user = UserService.authenticated();
		if (user==null) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		PageRequest pageRequest= new PageRequest(page, linePerPage, Direction.valueOf(direction), orderBy);
		Customer customer = customerRepository.findOne(user.getId());
		return orderRepository.findByCustomer(customer, pageRequest);
	}

}
