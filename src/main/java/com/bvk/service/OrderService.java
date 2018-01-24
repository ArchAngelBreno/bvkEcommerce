package com.bvk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvk.model.Order;
import com.bvk.repository.OrderRepository;
import com.bvk.service.exception.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	public Order findById(Long id) {
		Order order = orderRepository.findOne(id);
		if (order == null) {
			throw new ObjectNotFoundException("Não foi encontrado nenhum id: " + id + ", verifique com a gerencia");
		}
		return order;
	}

}
