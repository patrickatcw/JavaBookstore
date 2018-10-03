package com.JavaBookstore.JavaBookstore.domain.security;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    private int roleId;
    private String name;

    //onetomany creates a realtionship between the role class and userrole class
    //one role can have more than one userroles
    @OneToMany(mappedBy = "role", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
    //need to create UserRole class
    private Set<UserRole> userRoles = new HashSet<>();

    //getters and setters
    public int getRoleId() {

        return roleId;
    }

    public void setRoleId(int roleId) {

        this.roleId = roleId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


}
