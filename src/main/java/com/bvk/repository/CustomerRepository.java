package com.bvk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bvk.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Transactional(readOnly=true)
	Customer findByEmail(String email);

}
