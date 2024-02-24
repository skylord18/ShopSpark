package com.shopspark.ShopSpark.repository.inventory;

import com.shopspark.ShopSpark.entity.inventory.feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface featurerepository extends JpaRepository<feature, Integer> {
    @Query(value = "SELECT * FROM feature WHERE product_id = :productid", nativeQuery = true)
    List<feature> findByproductid(Integer productid);
}
