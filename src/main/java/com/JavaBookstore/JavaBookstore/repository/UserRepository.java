package com.JavaBookstore.JavaBookstore.repository;

//https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html

import com.JavaBookstore.JavaBookstore.domain.User;
import org.springframework.data.repository.CrudRepository;

//
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}