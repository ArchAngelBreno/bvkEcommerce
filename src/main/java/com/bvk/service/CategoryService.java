package com.bvk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvk.model.Category;
import com.bvk.repository.CategoryRepository;
import com.bvk.service.exception.ObjectNotFoundException;

@Service
public class CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category findById(Long id) {
		Category cat = categoryRepository.findOne(id);
		if (cat == null) {
			throw new ObjectNotFoundException("Não foi encontrado nenhum id: "+id + ", verifique com a gerencia");
		}
		return cat;
	}
}
