package com.bvk.model;

import java.util.Date;

import javax.persistence.Entity;

import com.bvk.enumerator.PaymentStatus;

@Entity
public class PaymentSlip extends Payment{

	private static final long serialVersionUID = 1L;
	
	private Date paymentDate;
	private Date dueDate;

	public PaymentSlip() {
	}

	public PaymentSlip(Long id, PaymentStatus paymentStatus, Order order,Date dueDate,Date paymentDate) {
		super(id, paymentStatus, order);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
}
