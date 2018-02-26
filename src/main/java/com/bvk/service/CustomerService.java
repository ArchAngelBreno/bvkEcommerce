package com.bvk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bvk.dto.CustomerDTO;
import com.bvk.model.Customer;
import com.bvk.repository.CustomerRepository;
import com.bvk.service.exception.DataIntegrityException;
import com.bvk.service.exception.ObjectNotFoundException;
import com.bvk.util.ConstantError;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer findById(Long id) {
		Customer cus = customerRepository.findOne(id);
		if (cus == null) {
			throw new ObjectNotFoundException(ConstantError.OBJECT_NOT_FOUND_ERROR + id);
		}
		return cus;
	}

	public Customer update(Customer customer) {
		Customer newCustomer = findById(customer.getId());
		updateData(newCustomer, customer);
		return customerRepository.save(newCustomer);
	}

	private void updateData(Customer newCustomer, Customer customer) {
		newCustomer.setName(customer.getName());
		newCustomer.setEmail(customer.getEmail());
	}

	public void delete(Long id) {
		findById(id);
		try {
			customerRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(ConstantError.DELETE_CUSTOMER_ERROR);
		}
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Page<Customer> findByPage(Integer page, Integer linePerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linePerPage, Direction.valueOf(direction), orderBy);
		return customerRepository.findAll(pageRequest);
	}

	public Customer fromDTO(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getId(), customerDTO.getNome(), customerDTO.getEmail(), null, null);
	}

}
