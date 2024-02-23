package com.shopspark.ShopSpark.service.inventory;

import com.shopspark.ShopSpark.entity.inventory.brand;
import com.shopspark.ShopSpark.repository.inventory.brandrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Deprecated
public class brandservice {
    @Autowired
    brandrepository brandrepository;
    public ResponseEntity<List<brand>> listllbrands() {
        try{
            return new ResponseEntity<>(brandrepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<brand>> getbrandbyid(Integer id) {
        try{
            Optional<brand> op = brandrepository.findById(id);
            return new ResponseEntity<>(op, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<brand> addbrand(brand brand) {
        try{
            brandrepository.save(brand);
            return new ResponseEntity<>(brand, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
