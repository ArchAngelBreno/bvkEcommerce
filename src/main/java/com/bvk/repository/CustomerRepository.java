package com.bvk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvk.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
