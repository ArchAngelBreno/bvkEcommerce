package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;

import com.bvk.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
