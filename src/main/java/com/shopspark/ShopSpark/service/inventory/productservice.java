package com.shopspark.ShopSpark.service.inventory;

import com.shopspark.ShopSpark.entity.inventory.product;
import com.shopspark.ShopSpark.repository.inventory.productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productservice {
    @Autowired
    productrepository productrepository;

    public ResponseEntity<List<product>> getallproducts() {
        try{
            return new ResponseEntity<>(productrepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<product>> getproductbyid(Integer id) {
        try{
            return new ResponseEntity<>(productrepository.findById(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<product> addProduct(product product) {
        try{
            productrepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<product>> getproductsbycategory(String category) {
        try{
            return new ResponseEntity<>(productrepository.findBycategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
