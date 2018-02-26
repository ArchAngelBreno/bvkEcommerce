package com.bvk.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.bvk.enumerator.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Payment extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private Integer paymentStatus;
	
	@OneToOne
	@JoinColumn(name="order_id")
	@MapsId
	@JsonIgnore
	private Order order;
	
	
	public Payment() {

	}


	public Payment(Long id, PaymentStatus paymentStatus, Order order) {
		super(id);
		this.paymentStatus = (paymentStatus == null) ? null : paymentStatus.getCod();
		this.order = order;
	}


	public PaymentStatus getPaymentStatus() {
		return PaymentStatus.toEnum(paymentStatus);
	}


	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus.getCod();
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
