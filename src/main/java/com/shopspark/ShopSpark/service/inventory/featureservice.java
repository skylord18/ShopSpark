package com.shopspark.ShopSpark.service.inventory;
import com.shopspark.ShopSpark.entity.inventory.feature;
import com.shopspark.ShopSpark.entity.inventory.product;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.repository.inventory.featurerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class featureservice {
    @Autowired
    featurerepository featurerepository;
    public ResponseEntity<List<feature>> listllfeatures() throws SomethingWentWrongException {
        try{
            return new ResponseEntity<>(featurerepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }
    public ResponseEntity<Optional<feature>> getfeaturebyid(Integer id) throws SomethingWentWrongException {
        try{
            Optional<feature> op = featurerepository.findById(id);
            return new ResponseEntity<>(op, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }
    public ResponseEntity<feature> addfeature(feature feature, Authentication authentication) throws SomethingWentWrongException {
        try{
            feature.setCreatedAt(LocalDateTime.now());
            feature.setCreatedBy(authentication.getPrincipal().toString());
            feature.setUpdatedAt(LocalDateTime.now());
            feature.setUpdatedBy(authentication.getPrincipal().toString());
            featurerepository.save(feature);
            return new ResponseEntity<>(feature, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }


    public ResponseEntity<List<feature>> getfeaturesbyproductid(Integer productid) throws SomethingWentWrongException {
        try{
            return new ResponseEntity<>(featurerepository.findByproductid(productid), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<feature> updatefeature(Integer id, feature feature, Authentication authentication) throws SomethingWentWrongException {
        Optional<feature> prevOptional = featurerepository.findById(id);
        feature prev = prevOptional.orElse(null);

        if(prev==null)throw new SomethingWentWrongException("The ID supplied is Invalid, Please check once again..");
        try {
            Integer prod_id = featurerepository.fetchProdId(id);
            prev.setProperty(feature.getProperty());
            prev.setValue(feature.getValue());
            prev.setUpdatedAt(LocalDateTime.now());
            prev.setUpdatedBy(authentication.getPrincipal().toString());
            featurerepository.save(prev);
            featurerepository.updateProductId(prod_id, id);
            //Fetch Prev Prod_id & Update it in the end
            return new ResponseEntity<>(feature, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

    public ResponseEntity<feature> deletefeaturebyid(Integer id) throws SomethingWentWrongException {
        try{
            Optional<feature> f = featurerepository.findById(id);
            featurerepository.deleteById(id);
            return new ResponseEntity<>(f.get(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Either the Feature with Supplied ID Does not Exist or Something Went Wrong. Try Again Later..");
        }
    }
}
