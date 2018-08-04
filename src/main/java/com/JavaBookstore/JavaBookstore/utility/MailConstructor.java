package com.JavaBookstore.JavaBookstore.utility;

import com.JavaBookstore.JavaBookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Locale;

//so this is the email that the new user receives when they create the account with credential

@Component
public class MailConstructor {
    @Autowired
    private Environment env;

    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user, String password
    ) {

        //in conjunction with homecontroller
        //String url = contextPath = "/newUser?token="+token;
        //had to change = to + to get link to work sent in initial user validation email
        //and in passwordresettoken had to add default oonstructor,  public PasswordResetToken(){}
        String url = contextPath + "/newUser?token="+token;
        String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"+password + " This " +
                "password will expire in 24 hours, so don't diddle daddle!";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Java Bookstore - New User");
        email.setText(url+message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }
}

