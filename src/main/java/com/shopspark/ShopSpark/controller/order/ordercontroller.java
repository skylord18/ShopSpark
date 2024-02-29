package com.shopspark.ShopSpark.controller.order;

import com.shopspark.ShopSpark.entity.order.order;
import com.shopspark.ShopSpark.exceptions.InvalidInputFormat;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.service.order.orderservice;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("order")
@Slf4j
public class ordercontroller {
    @Autowired
    orderservice orderservice;
    @PostMapping("placeorder")
    public ResponseEntity<order> placeorder(@Valid @RequestBody order order, Errors errors, Authentication authentication) throws Exception {
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
        return orderservice.placeorder(order, authentication);
    }
    @GetMapping("view")
    public ResponseEntity<List<order>> getmyoders(Authentication authentication) throws Exception {
        if(!authentication.isAuthenticated())throw new Exception("This page Requires User Credentials.");
        return orderservice.getmyoders(authentication);
    }
    @GetMapping("viewseller")
    public ResponseEntity<List<order>> getordersasseller(Authentication authentication) throws Exception {
        if(!authentication.isAuthenticated())throw new Exception("This page Requires Seller Credentials.");
        return orderservice.getordersasseller(authentication);
    }
    @PatchMapping("update/{id}/{orderStatus}")
    public ResponseEntity<order> updateorderstatus(Authentication authentication, @PathVariable("id") Integer id,@PathVariable("orderStatus") String orderStatus) throws Exception {
        return orderservice.updateorderstatus(authentication, id, orderStatus);
    }
}
