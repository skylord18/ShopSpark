package com.shopspark.ShopSpark.repository.inventory;

import com.shopspark.ShopSpark.entity.inventory.feature;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface featurerepository extends JpaRepository<feature, Integer> {
    @Query(value = "SELECT * FROM feature WHERE product_id = :productid", nativeQuery = true)
    List<feature> findByproductid(Integer productid);
    @Query(value = "SELECT product_id from feature WHERE id = :id", nativeQuery = true)
    Integer findProductId(Integer id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE feature SET product_id = :productId WHERE id = :id", nativeQuery = true)
    void updateProductId(Integer productId, Integer id);
    @Query(value = "SELECT product_id from feature WHERE id = :id", nativeQuery = true)
    Integer fetchProdId(Integer id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM feature WHERE product_id = :id", nativeQuery = true)
    void removefeaturesbyProductid(Integer id);
}
