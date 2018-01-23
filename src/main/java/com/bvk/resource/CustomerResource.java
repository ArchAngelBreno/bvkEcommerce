package com.bvk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvk.model.Customer;
import com.bvk.service.CustomerService;

@RestController
@RequestMapping("v1/customer")
public class CustomerResource {
	
	private CustomerService service;

	@Autowired
	public CustomerResource(CustomerService service) {
		super();
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Long id) {
		Customer c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}

}
