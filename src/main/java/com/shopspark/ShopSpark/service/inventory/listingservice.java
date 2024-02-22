package com.shopspark.ShopSpark.service.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import com.shopspark.ShopSpark.repository.inventory.listingrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class listingservice {

    @Autowired
    listingrepository listingrepository;
    public ResponseEntity<List<listing>> getallistings() {
        try{
            return new ResponseEntity<>(listingrepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<listing>> getlistingbyid(Integer id) {
        try{
            Optional<listing> op = listingrepository.findById(id);
            return new ResponseEntity<>(op, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<listing> addlisting(listing listing) {
        try{
            listingrepository.save(listing);
            return new ResponseEntity<>(listing, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<listing>> fetchListingsByProductId(Integer id) {
        try{
            return new ResponseEntity<>(listingrepository.findByproductId(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<listing>> fetchlistingsbysellerName(String sellerName) {
        try{
            return new ResponseEntity<>(listingrepository.findBySellerName(sellerName), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
