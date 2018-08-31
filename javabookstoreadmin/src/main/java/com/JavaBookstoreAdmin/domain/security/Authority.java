package com.JavaBookstoreAdmin.domain.security;

import org.springframework.security.core.GrantedAuthority;

//implements GrantedAuthority
public class Authority implements GrantedAuthority{

    //fields
    private final String authority;

    //constructor
    public Authority(String authority) {
        this.authority = authority;
    }

    //override method
    @Override
    public String getAuthority() {
        return authority;
    }
}
