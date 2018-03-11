package com.bvk.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.bvk.domain.PaymentSlip;

@Service
public class SlipService {

	public void completePaymentWithSlip(PaymentSlip slip, Date orderDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderDate);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		slip.setDueDate(cal.getTime());
	}
}
