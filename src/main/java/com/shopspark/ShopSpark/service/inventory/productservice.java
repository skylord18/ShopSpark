package com.shopspark.ShopSpark.service.inventory;

import com.shopspark.ShopSpark.entity.inventory.product;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.repository.inventory.featurerepository;
import com.shopspark.ShopSpark.repository.inventory.listingrepository;
import com.shopspark.ShopSpark.repository.inventory.productrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class productservice {
    @Autowired
    productrepository productrepository;
    @Autowired
    listingrepository listingrepository;
    @Autowired
    featurerepository featurerepository;
    @Deprecated
    public ResponseEntity<List<product>> getallproducts() throws SomethingWentWrongException {
        try{
            return new ResponseEntity<>(productrepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<Optional<product>> getproductbyid(Integer id) throws SomethingWentWrongException {
        try{
            return new ResponseEntity<>(productrepository.findById(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<product> addProduct(product product, Authentication authentication) throws SomethingWentWrongException {
        try{
            product.setCreatedAt(LocalDateTime.now());
            product.setCreatedBy(authentication.getPrincipal().toString());
            product.setUpdatedAt(LocalDateTime.now());
            product.setUpdatedBy(authentication.getPrincipal().toString());
            productrepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<Page<List<product>>> getproductsbycategory(String category, Integer pageno) throws SomethingWentWrongException {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5, Sort.by("name").ascending());
            return new ResponseEntity<>(productrepository.findBycategory(category, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }


    public ResponseEntity<Page<List<product>>> searchproductsbyname(String searchterm,  Integer pageno) throws SomethingWentWrongException {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5, Sort.by("name"));
            return new ResponseEntity<>(productrepository.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(searchterm, searchterm, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<Page<List<product>>> getalltheproducts(Integer pageno) throws SomethingWentWrongException {
        try{
            Pageable pageable = (Pageable) PageRequest.of(pageno,5, Sort.by("name"));
            return new ResponseEntity<>(productrepository.getalltheproducts(pageno, pageable), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<product> updateProduct(product product, Integer id, Authentication authentication) throws SomethingWentWrongException {
        Optional<product> op = productrepository.findById(id);
        product productfromdb= op.orElse(null);
        if(productfromdb == null)throw new SomethingWentWrongException("The Product ID is Invalid, Please Try Again");
        System.out.println(authentication.getAuthorities());
        if(!authentication.getAuthorities().contains("ROLE_ADMIN"))throw new SomethingWentWrongException("Not Authorized to Perform this Operation. Please Contact Admin to perform this action");
        product.setId(id);
        product.setUpdatedAt(LocalDateTime.now());
        product.setUpdatedBy(authentication.getPrincipal().toString());
        product.setCreatedAt(productfromdb.getCreatedAt());
        product.setCreatedBy(productfromdb.getCreatedBy());
        try{
            productrepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<String> deleteProduct(Integer id, Authentication authentication) throws SomethingWentWrongException {
        //if(!authentication.getAuthorities().contains("ROLE_ADMIN"))throw new SomethingWentWrongException("Not authorized to perform this operation. Please contact admin to perform this operation.");
        // Subsequently Go to Listing and Features and do the Cleanup from DB
        // Delete the product from DB
        Optional<product> op = productrepository.findById(id);
        product p = op.orElse(null);
        if(p==null)throw new SomethingWentWrongException("There seems to be an Issue with the product Id");
        try{
            listingrepository.removeListingsbyproductid(id);
            featurerepository.removefeaturesbyProductid(id);
            productrepository.deleteById(id);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }
}
