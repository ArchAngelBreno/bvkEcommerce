package com.bvk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bvk.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
