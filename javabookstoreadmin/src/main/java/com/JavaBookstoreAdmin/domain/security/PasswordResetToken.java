package com.JavaBookstoreAdmin.domain.security;

//binding token to user in a time window

import com.JavaBookstoreAdmin.domain.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;  //password expires in 24 hours, made note in message to new user

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //string token and user bind in a 1 to 1 relationship in a time period, expirytime
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable=false, name="user_id")
    private User user;

    private Date expiryDate;

    //had to add default constructor for link to work in new user validation email
    public PasswordResetToken(){}

    //constructor with parameters
    public PasswordResetToken(final String token, final User user) {
        super ();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    //method
    private Date calculateExpiryDate (final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    //method
    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    //getters and setters
    public Long getId()
    {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public static int getExpiration() {
        return EXPIRATION;
    }

    //generated toString method using the fields
    @Override
    public String toString() {
        return "PasswordResetToken [id=" + id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
                + "]";
    }


}
