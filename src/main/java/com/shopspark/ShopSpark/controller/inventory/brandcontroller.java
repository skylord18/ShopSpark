package com.shopspark.ShopSpark.controller.inventory;

import com.shopspark.ShopSpark.entity.inventory.brand;
import com.shopspark.ShopSpark.entity.inventory.feature;
import com.shopspark.ShopSpark.service.inventory.brandservice;
import com.shopspark.ShopSpark.service.inventory.featureservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("brand")
public class brandcontroller {
    @Autowired
    brandservice brandservice;

    @GetMapping("getall")
    public ResponseEntity<List<brand>> listllbrands(){
        return brandservice.listllbrands();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<brand>> getbrandbyid(@PathVariable("id") Integer id){
        return brandservice.getbrandbyid(id);
    }
    @PostMapping("add")
    public ResponseEntity<brand> addbrand(@RequestBody brand brand){
        return brandservice.addbrand(brand);
    }
}
