package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.BillingAddress;
import com.JavaBookstore.JavaBookstore.domain.UserBilling;

public interface BillingAddressService {

	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);

}				//now make impl
