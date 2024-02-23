package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.product;
import com.shopspark.ShopSpark.service.inventory.productservice;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("product")
@Slf4j
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
    public ResponseEntity<product> addProduct(@Valid @RequestBody product product, Errors errors){
        if(errors.hasErrors()){
            for(ObjectError obj : errors.getAllErrors())log.error(obj.getDefaultMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return productservice.addProduct(product);
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<product>> getproductsbycategory(@PathVariable("category") String category){
        return productservice.getproductsbycategory(category);
    }
}
