package com.bvk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvk.model.Category;
import com.bvk.service.CategoryService;

@RestController
@RequestMapping("v1/category")
public class CategoryResource {

	private CategoryService service;
	
	
	@Autowired
	public CategoryResource(CategoryService service) {
		super();
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Long id) {
		Category cat = service.findById(id);
		return ResponseEntity.ok().body(cat);
	}
	
}
