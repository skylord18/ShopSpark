package com.shopspark.ShopSpark.service.inventory;
import com.shopspark.ShopSpark.entity.inventory.feature;
import com.shopspark.ShopSpark.repository.inventory.featurerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class featureservice {
    @Autowired
    featurerepository featurerepository;
    public ResponseEntity<List<feature>> listllfeatures() {
        try{
            return new ResponseEntity<>(featurerepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<feature>> getfeaturebyid(Integer id) {
        try{
            Optional<feature> op = featurerepository.findById(id);
            return new ResponseEntity<>(op, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<feature> addfeature(feature feature) {
        try{
            featurerepository.save(feature);
            return new ResponseEntity<>(feature, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
