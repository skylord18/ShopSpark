package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.feature;
import com.shopspark.ShopSpark.exceptions.InvalidInputFormat;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
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
    public ResponseEntity<List<feature>> listllfeatures() throws SomethingWentWrongException {
        return featureservice.listllfeatures();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<feature>> getfeaturebyid(@PathVariable("id") Integer id) throws SomethingWentWrongException {
        return featureservice.getfeaturebyid(id);
    }
    @PostMapping("add")
    public ResponseEntity<feature> addfeature(@Valid @RequestBody feature feature, Errors errors) throws InvalidInputFormat, SomethingWentWrongException {
        if(errors.hasErrors()){
            StringBuilder sb = new StringBuilder("The following Errors Occurred:");
            for(ObjectError obj : errors.getAllErrors()){log.error(obj.getDefaultMessage());sb.append(obj.getDefaultMessage());sb.append(System.getProperty("line.separator"));}
            throw new InvalidInputFormat(sb.toString());
        }
        return featureservice.addfeature(feature);
    }
    @GetMapping("productid/{productid}")
    public ResponseEntity<List<feature>> getfeaturesbyproductid(@PathVariable("productid") Integer productid) throws SomethingWentWrongException {
        return featureservice.getfeaturesbyproductid(productid);
    }
}
