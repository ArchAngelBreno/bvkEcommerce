package com.bvk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Estate extends AbstractEntity{

	private static final long serialVersionUID = 1L;

	private String nome;
	
	@OneToMany(mappedBy="estate",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<City> cities = new ArrayList<>();
	
	public Estate() {
		
	}

	public Estate(Long id, String nome) {
		super(id);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
}
