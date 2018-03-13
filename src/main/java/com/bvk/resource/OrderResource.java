package com.bvk.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bvk.domain.Order;
import com.bvk.service.OrderService;

@RestController
@RequestMapping("v1/order")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order order = service.findById(id);
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Order obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<Page<Order>> findByPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linePerPage",defaultValue="24") Integer linePerPage,
			@RequestParam(value="orderBy",defaultValue="orderDate") String orderBy,
			@RequestParam(value="direction",defaultValue="DESC") String direction) {
		
		Page<Order> orders = service.findByPage(page, linePerPage, orderBy, direction);
		return ResponseEntity.ok().body(orders);
	}
	
	
}
