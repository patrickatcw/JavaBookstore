package com.JavaBookstore.JavaBookstore.service.impl;

//implements userservice interface

import com.JavaBookstore.JavaBookstore.domain.*;
import com.JavaBookstore.JavaBookstore.domain.security.PasswordResetToken;
import com.JavaBookstore.JavaBookstore.domain.security.UserRole;
import com.JavaBookstore.JavaBookstore.repository.*;
import com.JavaBookstore.JavaBookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    //defining logger
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    //need to define PasswordResetTokenRepository interface

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);

    }

    //used in homecontroller, parameter in userservice
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail (String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional  //added for binding shopping basket to user
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if(localUser != null) {
            //did below after defined logger above
            //for new user sign-up
            LOG.info("User {} already exists. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole()); //add rolerepository interface
            }

            user.getUserRoles().addAll(userRoles);

            ShoppingBasket shoppingBasket = new ShoppingBasket();     //added for binding shopping basket to user
            shoppingBasket.setUser(user);                         //added for binding shopping basket to user
            user.setShoppingBasket(shoppingBasket);                 //added for binding shopping basket to user

            user.setUserShippingList(new ArrayList<UserShipping>());        //added for binding shopping basket to user
            user.setUserPaymentList(new ArrayList<UserPayment>());          //added for binding shopping basket to user

            localUser = userRepository.save(user);
        }

        return localUser;
    }

    //homecontroller and userservice
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        userBilling.setUserPayment(userPayment);
        user.getUserPaymentList().add(userPayment);
        save(user);
    }

    //from userservice interface void updateUserShipping
    @Override
    public void updateUserShipping(UserShipping userShipping, User user){
        userShipping.setUser(user);
        userShipping.setUserShippingDefault(true);   //need to create method next
        user.getUserShippingList().add(userShipping);
        save(user);
    }

    @Override
    public void setUserDefaultPayment(Long userPaymentId, User user) {
        List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();
        //forget to autowire userpaymentrepository, do that now, done

        //checking id are same
        for (UserPayment userPayment : userPaymentList) {
            if(userPayment.getId() == userPaymentId) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
                //now check myprofile for setdefault payment
            }
        }
    }

    //from userservice impl
    @Override
    public void setUserDefaultShipping(Long userShippingId, User user) {
        List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();
        //need to autowire userShippingRepository in here, do now....done
        for (UserShipping userShipping : userShippingList) {
            if(userShipping.getId() == userShippingId) {
                userShipping.setUserShippingDefault(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setUserShippingDefault(false);
                userShippingRepository.save(userShipping);
            }
        }
    }

}



