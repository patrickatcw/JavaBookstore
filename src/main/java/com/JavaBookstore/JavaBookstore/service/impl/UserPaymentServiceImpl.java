package com.JavaBookstore.JavaBookstore.service.impl;

import com.JavaBookstore.JavaBookstore.domain.UserPayment;
import com.JavaBookstore.JavaBookstore.repository.UserPaymentRepository;
import com.JavaBookstore.JavaBookstore.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {

	@Autowired
	private UserPaymentRepository userPaymentRepository; //create userpaymentrepository
		
	public UserPayment findById(Long id) {

		UserPayment userPayment = userPaymentRepository.findById(id).get();
		return userPayment;

	}
} 
