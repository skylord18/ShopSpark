package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.feature;
import com.shopspark.ShopSpark.service.inventory.featureservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("feature")
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
    public ResponseEntity<feature> addfeature(@RequestBody feature feature){
        return featureservice.addfeature(feature);
    }

}
