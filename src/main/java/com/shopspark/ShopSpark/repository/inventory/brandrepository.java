package com.shopspark.ShopSpark.repository.inventory;

import com.shopspark.ShopSpark.entity.inventory.brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Deprecated
public interface brandrepository extends JpaRepository<brand, Integer> {
}
