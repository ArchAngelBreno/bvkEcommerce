package com.bvk.domain;

import javax.persistence.Entity;

import com.bvk.enumerator.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PaymentCard extends Payment {

	private static final long serialVersionUID = 1L;

	private Integer numberPlots;

	public PaymentCard() {
	}

	public PaymentCard(Long id, PaymentStatus paymentStatus, Order order, Integer numberPlots) {
		super(id, paymentStatus, order);
		this.numberPlots = numberPlots;
	}

	public Integer getNumberPlots() {
		return numberPlots;
	}

	public void setNumberPlots(Integer numberPlots) {
		this.numberPlots = numberPlots;
	}

}
