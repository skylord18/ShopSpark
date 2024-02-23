package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import com.shopspark.ShopSpark.service.inventory.listingservice;
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
@RequestMapping("listing")
@Slf4j
public class listingcontroller {
    @Autowired
    listingservice listingservice;
    @GetMapping("getall")
    public ResponseEntity<List<listing>> getallistings(){
        return listingservice.getallistings();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<listing>> getlistingbyid(@PathVariable("id") Integer id){
        return listingservice.getlistingbyid(id);
    }
    @PostMapping("add")
    public ResponseEntity<listing> addlisting(@Valid @RequestBody listing listing, Errors errors){
        if(errors.hasErrors()){
            for(ObjectError obj : errors.getAllErrors())log.error(obj.getDefaultMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return listingservice.addlisting(listing);
    }
    @GetMapping("productid/{productid}")
    public ResponseEntity<List<listing>> fetchListingsByProductId(@PathVariable("productid") Integer productid){
        return listingservice.fetchListingsByProductId(productid);
    }
    @GetMapping("seller/{sellerName}")
    public ResponseEntity<List<listing>> fetchlistingsbyseller(@PathVariable("sellerName") String sellerName){
        return listingservice.fetchlistingsbysellerName(sellerName);
    }

}
