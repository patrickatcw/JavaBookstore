package com.JavaBookstore.JavaBookstore.service.impl;

//implements userservice interface

import com.JavaBookstore.JavaBookstore.domain.User;
import com.JavaBookstore.JavaBookstore.domain.security.PasswordResetToken;
import com.JavaBookstore.JavaBookstore.domain.security.UserRole;
import com.JavaBookstore.JavaBookstore.repository.PasswordResetTokenRepository;
import com.JavaBookstore.JavaBookstore.repository.RoleRepository;
import com.JavaBookstore.JavaBookstore.repository.UserRepository;
import com.JavaBookstore.JavaBookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            localUser = userRepository.save(user);
        }

        return localUser;
    }

    //homecontroller and userservice
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}



