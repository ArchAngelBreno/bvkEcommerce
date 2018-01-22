package com.bvk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Double preco;
	
	//Nao busco a lista de categorias, pois ja esta sendo chamada do outro lado
	@JsonBackReference
	@ManyToMany
	@JoinTable(name="product_category",
				joinColumns= @JoinColumn(name="product_id"),
				inverseJoinColumns=@JoinColumn(name="category_id")
			)
	private List<Category> categories = new ArrayList<Category>();

	public Product() {
		
	}

	public Product(Long id, String nome, Double preco) {
		super(id);
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
