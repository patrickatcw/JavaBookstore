package com.JavaBookstore.JavaBookstore.service.impl;

import com.JavaBookstore.JavaBookstore.domain.UserShipping;
import com.JavaBookstore.JavaBookstore.repository.UserShippingRepository;
import com.JavaBookstore.JavaBookstore.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {
	
	@Autowired
	private UserShippingRepository userShippingRepository;  //define usershippingrepository
	
	
	public UserShipping findById(Long id) {

		UserShipping userShipping = userShippingRepository.findById(id).get();
		return userShipping;

	}
	
	public void removeById(Long id) {

		userShippingRepository.deleteById(id);

	}

}
