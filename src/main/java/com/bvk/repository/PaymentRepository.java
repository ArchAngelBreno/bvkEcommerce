package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
