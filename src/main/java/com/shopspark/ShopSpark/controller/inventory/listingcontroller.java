package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import com.shopspark.ShopSpark.service.inventory.listingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("listing")
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
    public ResponseEntity<listing> addlisting(@RequestBody listing listing){
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
