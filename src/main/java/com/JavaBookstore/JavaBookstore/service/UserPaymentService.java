package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.UserPayment;

public interface UserPaymentService {

	UserPayment findById(Long id); //now create userpaymentserviceimpl

	void removeById(Long id); //then go to userpaymentserviceimpl

}


