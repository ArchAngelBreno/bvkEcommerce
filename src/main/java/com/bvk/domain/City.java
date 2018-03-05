package com.bvk.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class City extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	private String nome;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="estate_id")
	private Estate estate;

	public City() {
		
	}
	
	public City(Long id, String nome, Estate estate) {
		super(id);
		this.nome = nome;
		this.estate = estate;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

}
