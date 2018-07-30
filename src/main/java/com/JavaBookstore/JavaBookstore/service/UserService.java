package com.JavaBookstore.JavaBookstore.service;

import com.JavaBookstore.JavaBookstore.domain.User;
import com.JavaBookstore.JavaBookstore.domain.security.PasswordResetToken;
import com.JavaBookstore.JavaBookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void createPasswordResetTokenForUser(final User user, final String token);

    //used in homecontroller, define in userserviceimpl
    User findByUsername(String username);

    User findByEmail (String email);

    //homecontroller
    User createUser(User user, Set<UserRole> userRoles) throws Exception;

}