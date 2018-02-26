package com.bvk.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.bvk.model.Customer;

public class CustomerDTO {

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=120,message="O nome deve conter entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;

	public CustomerDTO() {
	}

	public CustomerDTO(Customer obj) {
		id = obj.getId();
		nome = obj.getName();
		setEmail(obj.getEmail());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
