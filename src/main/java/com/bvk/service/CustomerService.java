package com.bvk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvk.model.Customer;
import com.bvk.repository.CustomerRepository;
import com.bvk.service.exception.ObjectNotFoundException;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findById(Long id) {
		Customer cus = customerRepository.findOne(id);
		if (cus == null) {
			throw new ObjectNotFoundException("Não foi encontrado nenhum id: "+id + ", verifique com a gerencia");
		}
		return cus;
	}

}
