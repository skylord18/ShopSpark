package com.shopspark.ShopSpark.repository.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface listingrepository extends JpaRepository<listing, Integer> {
    Page<List<listing>> findBySellerName(String sellerName, Pageable pageable);
    Page<List<listing>> findByproductId(Integer id, Pageable pageable);
    @Query(value = "SELECT * FROM listing", nativeQuery = true)
    Page<List<listing>> getallistings(Integer pageno, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "UPDATE listing SET product_id = :productId WHERE id =:listingId", nativeQuery = true)
    void setProductid(Integer listingId, Integer productId);

    List<listing> findByCreatedBy(String email);
    @Query(value = "SELECT product_id from listing WHERE id = :id", nativeQuery = true)
    Integer getProdId(Integer id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM listing WHERE product_id = :idx", nativeQuery = true)
    void removeListingsbyproductid(Integer idx);
}
