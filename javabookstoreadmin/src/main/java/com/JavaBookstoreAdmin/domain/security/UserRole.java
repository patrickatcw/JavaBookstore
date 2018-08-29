package com.JavaBookstoreAdmin.domain.security;

import com.JavaBookstoreAdmin.domain.User;

import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    //field
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    //field
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    //field
    private Role role;

    //had to include this as a default constructor to fix error....
    //Caused by: org.springframework.orm.jpa.JpaSystemException: No default constructor for entity:
    // : com.JavaBookstore.JavaBookstore.domain.security.UserRole;
    //https://stackoverflow.com/questions/44088360/org-hibernate-instantiationexception-no-default-constructor-for-entity-princ
    public UserRole(){}
    //default constructor defined in conjunction with parameter-based constructor
    //perameter based constructor
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }


    //getters and setters
    public Long getUserRoleId() {
        return userRoleId;
    }


    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


}
