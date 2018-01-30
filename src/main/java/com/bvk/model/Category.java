package com.bvk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;


@Entity
public class Category extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String nome;
	
	@ManyToMany(mappedBy="categories")
	private List<Product> products = new ArrayList<>();

	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(String nome) {
		super();
		this.nome = nome;
	}



	public Category(Long id, String nome) {
		super(id);
		this.nome = nome;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
