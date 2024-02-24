package com.shopspark.ShopSpark.repository.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface listingrepository extends JpaRepository<listing, Integer> {
    Page<List<listing>> findBySellerName(String sellerName, Pageable pageable);
    Page<List<listing>> findByproductId(Integer id, Pageable pageable);
    @Query(value = "SELECT * FROM listing", nativeQuery = true)
    Page<List<listing>> getallistings(Integer pageno, Pageable pageable);
}
