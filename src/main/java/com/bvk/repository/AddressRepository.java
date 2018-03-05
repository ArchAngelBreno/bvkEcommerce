package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.domain.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{

}
