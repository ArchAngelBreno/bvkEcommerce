package com.bvk.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvk.domain.OrderItem;

@Repository	
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
