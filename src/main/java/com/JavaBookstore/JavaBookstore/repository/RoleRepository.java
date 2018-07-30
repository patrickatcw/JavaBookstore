package com.JavaBookstore.JavaBookstore.repository;

import com.JavaBookstore.JavaBookstore.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByname(String name);
}
