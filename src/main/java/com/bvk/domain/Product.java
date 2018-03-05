package com.bvk.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Double preco;
	
	//Nao busco a lista de categorias, pois ja esta sendo chamada do outro lado
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="product_category",
				joinColumns= @JoinColumn(name="product_id"),
				inverseJoinColumns=@JoinColumn(name="category_id")
			)
	private List<Category> categories = new ArrayList<Category>();
	
	@JsonIgnore
	@OneToMany(mappedBy="id.product")
	private Set<OrderItem> items = new HashSet<>();

	public Product() {
		
	}

	public Product(Long id, String nome, Double preco) {
		super(id);
		this.nome = nome;
		this.preco = preco;
	}

	@JsonIgnore
	public List<Order> getOrders(){
		List<Order> orders = new ArrayList<>();
		for (OrderItem x : items) {
			orders.add(x.getOrder());
		}
		
		return orders;
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

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

}
