package com.bvk.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bvk.domain.Category;
import com.bvk.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	//@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.nome LIKE %:nome% AND cat IN :categories")
	@Transactional(readOnly=true)
	Page<Product> findDistinctByNomeContainingAndCategoriesIn(/*@Param("nome")*/ String name, /*@Param("categories")*/List<Category> categories, Pageable pageRequest);

}
