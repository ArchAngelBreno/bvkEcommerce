package com.bvk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bvk.domain.Address;
import com.bvk.domain.City;
import com.bvk.domain.Customer;
import com.bvk.dto.CustomerDTO;
import com.bvk.dto.NewCustomerDTO;
import com.bvk.enumerator.CustomerType;
import com.bvk.enumerator.Profile;
import com.bvk.repository.AddressRepository;
import com.bvk.repository.CityRepository;
import com.bvk.repository.CustomerRepository;
import com.bvk.security.UserSS;
import com.bvk.service.exception.AuthorizationException;
import com.bvk.service.exception.DataIntegrityException;
import com.bvk.service.exception.ObjectNotFoundException;
import com.bvk.util.ConstantError;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Customer findById(Long id) {
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		
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

	public Customer insert(Customer cus) {
		cus.setId(null);
		cus = customerRepository.save(cus);
		addressRepository.save(cus.getAddresses());
		return cus;
	}

	public Customer fromDTO(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getId(), customerDTO.getNome(), customerDTO.getEmail(), null, null,null);
	}

	public Customer fromDTO(NewCustomerDTO customerDTO) {
		Customer cus = new Customer(null, customerDTO.getName(), customerDTO.getEmail(), customerDTO.getCpfOrCnpj(),
				CustomerType.toEnum(customerDTO.getCustomerType()),encoder.encode(customerDTO.getPassword()));
		City city = cityRepository.findOne(customerDTO.getCityId());
		Address address = new Address(null, customerDTO.getStreet(), customerDTO.getNumber(),
				customerDTO.getComplement(), customerDTO.getNeighborhood(), customerDTO.getZipcode(), cus, city);
		cus.getAddresses().add(address);
		cus.getPhones().add(customerDTO.getPhone1());

		if (customerDTO.getPhone2() != null) {
			cus.getPhones().add(customerDTO.getPhone2());
		}

		if (customerDTO.getPhone3() != null) {
			cus.getPhones().add(customerDTO.getPhone3());
		}

		return cus;
	}

}
