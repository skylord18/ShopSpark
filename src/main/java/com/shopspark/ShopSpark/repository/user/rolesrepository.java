package com.shopspark.ShopSpark.repository.user;

import com.shopspark.ShopSpark.entity.user.roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rolesrepository extends JpaRepository<roles, Integer> {
    roles getByrolename(String rolename);
}
