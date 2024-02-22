package com.shopspark.ShopSpark.repository.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface listingrepository extends JpaRepository<listing, Integer> {
    List<listing> findBySellerName(String sellerName);
    List<listing> findByproductId(Integer id);
}
