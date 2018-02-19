package com.bvk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bvk.dto.CategoryDTO;
import com.bvk.model.Category;
import com.bvk.repository.CategoryRepository;
import com.bvk.service.exception.DataIntegrityException;
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

	public Category insert(Category cat) {
		cat.setId(null);
		return categoryRepository.save(cat);
	}

	public Category update(Category cat) {
		findById(cat.getId());
		return categoryRepository.save(cat);
	}

	public void delete(Long id) {
		findById(id);
		try {
			categoryRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	
	public Page<Category> findByPage(Integer page, Integer linePerPage,String orderBy,String direction){
		PageRequest pageRequest= new PageRequest(page, linePerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getNome());
	}
	
}
