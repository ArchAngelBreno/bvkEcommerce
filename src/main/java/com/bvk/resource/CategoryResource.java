package com.bvk.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bvk.domain.Category;
import com.bvk.dto.CategoryDTO;
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
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category cat = service.findById(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> categories = service.findAll();
		List<CategoryDTO> listDto = 
				categories.stream().map(obj-> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoryDTO>> findByPage(
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linePerPage",defaultValue="24") Integer linePerPage,
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy,
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		Page<Category> categories = service.findByPage(page, linePerPage, orderBy, direction);
		Page<CategoryDTO> listDto = 
				categories.map(obj-> new CategoryDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO catDto){
		Category cat = service.fromDTO(catDto);
		cat = service.insert(cat);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cat.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO catDTO,@PathVariable Long id){
		Category cat = service.fromDTO(catDTO);
		cat.setId(id);
		cat = service.update(cat);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
