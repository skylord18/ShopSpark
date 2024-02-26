package com.shopspark.ShopSpark.repository.user;


import com.shopspark.ShopSpark.entity.user.person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface personrepository extends JpaRepository<person, Integer> {
//    @Query(value = "SELECT * FROM person where email = :email", nativeQuery = true)
    person findByEmail(String email);
}
