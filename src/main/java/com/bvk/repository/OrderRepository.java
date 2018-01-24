package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}
