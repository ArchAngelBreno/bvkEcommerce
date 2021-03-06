package com.bvk.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.bvk.enumerator.CustomerType;
import com.bvk.enumerator.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer customerIndentifier;
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Address> addresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name="phone")
	private Set<String> phones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PROFILE")
	private Set<Integer> profiles = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Order> orders = new ArrayList<>();

	public Customer() {
		addProfile(Profile.CLIENT);
	}

	public Customer(Long id, String name, String email, String cpfOrCnpj, CustomerType customerIndentifier,String password) {
		super(id);
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.password=password;
		this.customerIndentifier = (customerIndentifier == null) ? null : customerIndentifier.getCod();
		addProfile(Profile.CLIENT);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}


	public CustomerType getCustomerIndentifier() {
		return CustomerType.toEnum(customerIndentifier);
	}

	public void setCustomerIndentifier(CustomerType customerIndentifier) {
		this.customerIndentifier = customerIndentifier.getCod();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setCustomerType(Integer customerType) {
		this.customerIndentifier = customerType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		profiles.add(profile.getCod());
	}

	public void setProfiles(Set<Integer> profiles) {
		this.profiles = profiles;
	}
}
