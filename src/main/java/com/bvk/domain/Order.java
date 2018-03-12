package com.bvk.domain;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
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
@Table(name = "order_table")
public class Order extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date orderDate;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "shippingAddress_id")
	private Address shippingAddress;

	@OneToMany(mappedBy = "id.order")
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

	public Double getFinalAmount() {
		double soma = 0.0;
		for (OrderItem orderItem : items) {
			soma = soma + orderItem.getSubTotal();
		}

		return soma;
	}

	@Override
	public String toString() {
		final NumberFormat NF = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Data do pedido: ");
		builder.append(SDF.format(getOrderDate()));
		builder.append(", Cliente: ");
		builder.append(getCustomer().getName());
		builder.append(", Situação do pagamento: ");
		builder.append(getPayment().getPaymentStatus().getDesc());
		builder.append("\n, Detalhes: \n");
		for (OrderItem oi : getItems()) {
			builder.append(oi.toString());
		}
		builder.append("Valor total: ");
		builder.append(NF.format(getFinalAmount()));
		return builder.toString();
	}
	
}
