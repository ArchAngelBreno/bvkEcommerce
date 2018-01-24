package com.bvk.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.model.OrderItem;

@Repository	
public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

}
