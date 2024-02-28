package com.shopspark.ShopSpark.repository.inventory;

import com.shopspark.ShopSpark.entity.inventory.product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface productrepository extends JpaRepository<product, Integer> {
    @Query(value = "SELECT * FROM product", nativeQuery = true)
    Page<List<product>> getalltheproducts(Integer pageno, Pageable pageable);
    Page<List<product>> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(String searchterm, String searchterm1, Pageable pageable);
    Page<List<product>> findBycategory(String category, Pageable pageable);

}
