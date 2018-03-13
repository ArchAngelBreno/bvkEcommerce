package com.bvk.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EmailDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Email(message="Email deve ser preenchido corretamente")
	@NotEmpty(message="Preenchimento obrigatorio")
	private String email;

	public EmailDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
