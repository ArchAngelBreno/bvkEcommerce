package com.bvk.dto;

import java.io.Serializable;

import com.bvk.model.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;

	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CategoryDTO(Category cat) {
		id = cat.getId();
		nome = cat.getNome();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
