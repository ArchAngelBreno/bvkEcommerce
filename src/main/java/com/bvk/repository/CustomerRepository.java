package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
