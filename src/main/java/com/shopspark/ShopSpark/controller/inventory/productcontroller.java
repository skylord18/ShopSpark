package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.product;
import com.shopspark.ShopSpark.service.inventory.productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class productcontroller {
    @Autowired
    productservice productservice;
    @GetMapping("getall")
    public ResponseEntity<List<product>> getallproducts(){
        return productservice.getallproducts();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<product>> getproductbyid(@PathVariable("id") Integer id){
        return productservice.getproductbyid(id);
    }
    @PostMapping("add")
    public ResponseEntity<product> addProduct(@RequestBody product product){
        return productservice.addProduct(product);
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<product>> getproductsbycategory(@PathVariable("category") String category){
        return productservice.getproductsbycategory(category);
    }
}
