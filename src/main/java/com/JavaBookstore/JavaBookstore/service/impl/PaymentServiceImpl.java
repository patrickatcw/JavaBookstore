package com.JavaBookstore.JavaBookstore.service.impl;

import com.JavaBookstore.JavaBookstore.domain.Payment;
import com.JavaBookstore.JavaBookstore.domain.UserPayment;
import com.JavaBookstore.JavaBookstore.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	public Payment setByUserPayment(UserPayment userPayment, Payment payment) {
		payment.setType(userPayment.getType());
		payment.setHolderName(userPayment.getHolderName());
		payment.setCardNumber(userPayment.getCardNumber());
		payment.setExpiryMonth(userPayment.getExpiryMonth());
		payment.setExpiryYear(userPayment.getExpiryYear());
		payment.setCvv(userPayment.getCvv());
		
		return payment;
	}

}
