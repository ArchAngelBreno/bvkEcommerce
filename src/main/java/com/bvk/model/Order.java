package com.bvk.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="order_table")
public class Order extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date orderDate;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "shippingAddress_id")
	private Address shippingAddress;
	
	@OneToMany(mappedBy="id.order")
	private Set<OrderItem> items = new HashSet<>();

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, Date orderDate, Payment payment, Customer customer, Address shippingAddress) {
		super(id);
		this.orderDate = orderDate;
		this.payment = payment;
		this.customer = customer;
		this.shippingAddress = shippingAddress;
	}
	
	

	public Order(Long id, Date orderDate, Customer customer, Address shippingAddress) {
		super(id);
		this.orderDate = orderDate;
		this.customer = customer;
		this.shippingAddress = shippingAddress;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

}
