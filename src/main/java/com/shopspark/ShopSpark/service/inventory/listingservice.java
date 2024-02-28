package com.shopspark.ShopSpark.service.inventory;

import com.shopspark.ShopSpark.entity.inventory.listing;
import com.shopspark.ShopSpark.exceptions.InvalidInputFormat;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.repository.inventory.listingrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class listingservice {

    @Autowired
    listingrepository listingrepository;
    public ResponseEntity<Page<List<listing>>> getallistings(Integer pageno) throws SomethingWentWrongException {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5,
                    Sort.by("price").ascending().and(Sort.by("avl_qty").descending()));
            return new ResponseEntity<>(listingrepository.getallistings(pageno, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<Optional<listing>> getlistingbyid(Integer id) throws SomethingWentWrongException {
        try{
            Optional<listing> op = listingrepository.findById(id);
            return new ResponseEntity<>(op, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<listing> addlisting(listing listing, Authentication authentication, Integer productId) throws SomethingWentWrongException {
        try{
            listing.setCreatedAt(LocalDateTime.now());
            listing.setCreatedBy(authentication.getPrincipal().toString());
            listing.setUpdatedAt(LocalDateTime.now());
            listing.setUpdatedBy(authentication.getPrincipal().toString());
            listingrepository.save(listing);
            Integer listingId = listing.getId();
            listingrepository.setProductid(listingId, productId);
            return new ResponseEntity<>(listing, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<Page<List<listing>>> fetchListingsByProductId(Integer id, Integer pageno) throws SomethingWentWrongException {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5,
                    Sort.by("price").ascending().and(Sort.by("avlQty").descending()));
            return new ResponseEntity<>(listingrepository.findByproductId(id, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<Page<List<listing>>> fetchlistingsbysellerName(String sellerName, Integer pageno) throws SomethingWentWrongException {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5,
                    Sort.by("price").ascending().and(Sort.by("avlQty").descending()));
            return new ResponseEntity<>(listingrepository.findBySellerName(sellerName, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<List<listing>> getmylistings(Authentication authentication) throws SomethingWentWrongException {
        try{
            String email = authentication.getName().toString();
            return new ResponseEntity<>(listingrepository.findByCreatedBy(email),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<listing> modifylisting(Authentication authentication, Integer id, listing listing) throws InvalidInputFormat, SomethingWentWrongException {
        Optional<listing> Listingfromrepo = listingrepository.findById(id);
        if(Listingfromrepo.isEmpty())throw new InvalidInputFormat("Listing with the supplied ID does not exist.");
        listing Listing = Listingfromrepo.orElse(null);
        String createdBy = Listing.getCreatedBy();
        if(createdBy.equals(authentication.getPrincipal().toString())){
            Integer prod_id = listingrepository.getProdId(id);
            Listing.setSellerName(listing.getSellerName());
            Listing.setPrice(listing.getPrice());
            Listing.setAvlQty(listing.getAvlQty());
            Listing.setIsActive(listing.getIsActive());
            Listing.setUpdatedAt(LocalDateTime.now());
            Listing.setUpdatedBy(authentication.getPrincipal().toString());
            listingrepository.save(Listing);
            listingrepository.setProductid(id, prod_id);
            return new ResponseEntity<>(listing, HttpStatus.OK);
        }else{
            throw new SomethingWentWrongException("You have not Created This Listing...");
        }
    }
}
