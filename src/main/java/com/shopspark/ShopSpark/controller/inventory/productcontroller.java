package com.shopspark.ShopSpark.controller.inventory;


import com.shopspark.ShopSpark.entity.inventory.product;
import com.shopspark.ShopSpark.exceptions.InvalidInputFormat;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.service.inventory.productservice;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("product")
@Slf4j
public class productcontroller {
    @Autowired
    productservice productservice;
    @Deprecated
    @GetMapping("getall")
    public ResponseEntity<List<product>> getallproducts() throws SomethingWentWrongException {
        return productservice.getallproducts();
    }
    @GetMapping({"getall/{pageno}", "getall/page/{pageno}"})
    public ResponseEntity<Page<List<product>>> getalltheproducts(@PathVariable("pageno") Integer pageno) throws SomethingWentWrongException {
        return productservice.getalltheproducts(pageno);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<product>> getproductbyid(@PathVariable("id") Integer id) throws SomethingWentWrongException {
        return productservice.getproductbyid(id);
    }
    @PostMapping("add")
    public ResponseEntity<product> addProduct(@Valid @RequestBody product product, Errors errors, Authentication authentication) throws Exception {
        if(errors.hasErrors()){
            StringBuilder sb  = new StringBuilder("The following Error(s) Occurred:");
            sb.append(System.getProperty("line.separator"));
            for(ObjectError obj : errors.getAllErrors()){
                log.error(obj.getDefaultMessage());
                sb.append(obj.getDefaultMessage());
                sb.append(System.getProperty("line.separator"));
            }
            throw new InvalidInputFormat(sb.toString());
        }
        return productservice.addProduct(product, authentication);
    }
    @GetMapping({"category/{category}/{pageno}", "category/{category}/page/{pageno}"})
    public ResponseEntity<Page<List<product>>> getproductsbycategory(@PathVariable("category") String category, @PathVariable("pageno") Integer pageno) throws SomethingWentWrongException {
        return productservice.getproductsbycategory(category, pageno);
    }
    @GetMapping({"search/{searchterm}/page/{pageno}", "search/{searchterm}/{pageno}"})
    public ResponseEntity<Page<List<product>>> searchproductsbyname(@PathVariable("searchterm") String searchterm,@PathVariable("pageno") Integer pageno) throws SomethingWentWrongException {
        return productservice.searchproductsbyname(searchterm, pageno);
    }
}
