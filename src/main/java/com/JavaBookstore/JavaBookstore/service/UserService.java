package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.User;
import com.JavaBookstore.JavaBookstore.domain.UserBilling;
import com.JavaBookstore.JavaBookstore.domain.UserPayment;
import com.JavaBookstore.JavaBookstore.domain.UserShipping;
import com.JavaBookstore.JavaBookstore.domain.security.PasswordResetToken;
import com.JavaBookstore.JavaBookstore.domain.security.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    //used in homecontroller, define in userserviceimpl
    User findByUsername(String username);

    User findByEmail (String email);

    //homecontroller
    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //homecontroller
    User save(User user);

    //for addnewcredtcard in homecontroller
    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
    //now go into user service impl

    //for defaultcreditcard
    void setUserDefaultPayment(Long userPaymentId, User user);  //then to userserviceimpl

    //for addNewShippingAddress, then go to userserviceimpl
    void updateUserShipping(UserShipping userShipping, User user);

    //for @RequestMapping(value="/setDefaultShippingAddress"
    void setUserDefaultShipping(Long userShippingId, User user);
    //now to the userservice impl
}