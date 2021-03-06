package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.domain.Estate;

@Repository
public interface EstateRepository extends CrudRepository<Estate, Long>{

}
