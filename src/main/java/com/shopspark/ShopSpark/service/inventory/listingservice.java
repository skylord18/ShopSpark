package com.shopspark.ShopSpark.service.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import com.shopspark.ShopSpark.repository.inventory.listingrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class listingservice {

    @Autowired
    listingrepository listingrepository;
    public ResponseEntity<Page<List<listing>>> getallistings(Integer pageno) {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5,
                    Sort.by("price").ascending().and(Sort.by("avl_qty").descending()));
            return new ResponseEntity<>(listingrepository.getallistings(pageno, pageable), HttpStatus.OK);
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
            listing.setCreatedAt(LocalDateTime.now());
            listing.setCreatedBy("dummy");
            listing.setUpdatedAt(LocalDateTime.now());
            listing.setUpdatedBy("dummy");
            listingrepository.save(listing);
            return new ResponseEntity<>(listing, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Page<List<listing>>> fetchListingsByProductId(Integer id, Integer pageno) {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5,
                    Sort.by("price").ascending().and(Sort.by("avl_qty").descending()));
            return new ResponseEntity<>(listingrepository.findByproductId(id, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Page<List<listing>>> fetchlistingsbysellerName(String sellerName, Integer pageno) {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5,
                    Sort.by("price").ascending().and(Sort.by("avl_qty").descending()));
            return new ResponseEntity<>(listingrepository.findBySellerName(sellerName, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
