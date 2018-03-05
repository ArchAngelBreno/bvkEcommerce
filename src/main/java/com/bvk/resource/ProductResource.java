package com.bvk.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvk.domain.Product;
import com.bvk.dto.ProductDTO;
import com.bvk.service.ProductService;
import com.bvk.util.URLUtils;

@RestController
@RequestMapping("v1/product")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findByPage(@RequestParam(value = "name", defaultValue = "0") String name,
			@RequestParam(value = "categories", defaultValue = "0") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nameDecoded = URLUtils.decodeParam(name);
		List<Long> ids = URLUtils.decodeLongList(categories);
		Page<Product> products = service.search(nameDecoded, ids, page, linePerPage, orderBy, direction);
		Page<ProductDTO> listDto = products.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
