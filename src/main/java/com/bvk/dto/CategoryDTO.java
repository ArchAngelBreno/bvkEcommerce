package com.bvk.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.bvk.domain.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=80, message="O campo deve conter no minimo 5 caracteres")
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
