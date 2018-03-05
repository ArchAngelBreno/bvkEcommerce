package com.bvk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bvk.domain.Category;
import com.bvk.domain.Product;
import com.bvk.repository.CategoryRepository;
import com.bvk.repository.ProductRepository;
import com.bvk.service.exception.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Product findById(Long id) {
		Product product = productRepository.findOne(id);
		if (product == null) {
			throw new ObjectNotFoundException("Não foi encontrado nenhum id: " + id + ", verifique com a gerencia");
		}
		return product;
	}
	
	public Page<Product> search(String name, List<Long> ids, Integer page, Integer linePerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = new PageRequest(page, linePerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAll(ids);
		
		
		return productRepository.findDistinctByNomeContainingAndCategoriesIn(name,categories,pageRequest);
	}
}
