package com.bvk.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bvk.domain.Customer;
import com.bvk.dto.CustomerDTO;
import com.bvk.dto.NewCustomerDTO;
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
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> customers = service.findAll();
		List<CustomerDTO> listDto = 
				customers.stream().map(obj-> new CustomerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/page")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Page<CustomerDTO>> findByPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linePerPage",defaultValue="24") Integer linePerPage,
			@RequestParam(value="orderBy",defaultValue="name") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		Page<Customer> customers = service.findByPage(page, linePerPage, orderBy, direction);
		Page<CustomerDTO> listDto = 
				customers.map(obj-> new CustomerDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody NewCustomerDTO customerDTO){
		Customer cus = service.fromDTO(customerDTO);
		cus = service.insert(cus);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cus.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO catDTO,@PathVariable Long id){
		Customer customer = service.fromDTO(catDTO);
		customer.setId(id);
		customer = service.update(customer);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
