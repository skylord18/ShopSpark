package com.shopspark.ShopSpark.repository.order;

import com.shopspark.ShopSpark.entity.order.order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderrepository extends JpaRepository<order, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE ecommerce_order SET order_status =:orderstatus WHERE id = :id", nativeQuery = true)
    void updateorderStatus(Integer id, String orderstatus);

    List<order> findByCreatedBy(String string);


    List<order> findBySellerDetails(String string);
}
