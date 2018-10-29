package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.Payment;
import com.JavaBookstore.JavaBookstore.domain.UserPayment;

public interface PaymentService {

	Payment setByUserPayment(UserPayment userPayment, Payment payment);

}			//now make impl
