package com.shopspark.ShopSpark.controller.user;

import com.shopspark.ShopSpark.entity.user.person;
import com.shopspark.ShopSpark.exceptions.EmailAlreadyExistsException;
import com.shopspark.ShopSpark.exceptions.InvalidInputFormat;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.service.user.userservice;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("user")
@Slf4j
public class usercontroller {
    @Autowired
    userservice userservice;
    @PostMapping("add")
    public ResponseEntity<person> registerUser(@Valid @RequestBody person person, Errors errors) throws InvalidInputFormat, EmailAlreadyExistsException, SomethingWentWrongException {
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
        return userservice.registerUser(person);
    }
}
