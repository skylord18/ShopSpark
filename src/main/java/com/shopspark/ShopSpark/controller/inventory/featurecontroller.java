package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.feature;
import com.shopspark.ShopSpark.service.inventory.featureservice;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("feature")
@Slf4j
public class featurecontroller {

    @Autowired
    featureservice featureservice;
    @GetMapping("getall")
    public ResponseEntity<List<feature>> listllfeatures(){
        return featureservice.listllfeatures();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<feature>> getfeaturebyid(@PathVariable("id") Integer id){
        return featureservice.getfeaturebyid(id);
    }
    @PostMapping("add")
    public ResponseEntity<feature> addfeature(@Valid @RequestBody feature feature, Errors errors){
        if(errors.hasErrors()){
            for(ObjectError obj : errors.getAllErrors())log.error(obj.getDefaultMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return featureservice.addfeature(feature);
    }
}
