package com.bvk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvk.model.Order;
import com.bvk.service.OrderService;

@RestController
@RequestMapping("v1/order")
public class OrderResource {
	

	private OrderService service;
	
	
	@Autowired
	public OrderResource(OrderService service) {
		super();
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order order = service.findById(id);
		return ResponseEntity.ok().body(order);
	}

}
