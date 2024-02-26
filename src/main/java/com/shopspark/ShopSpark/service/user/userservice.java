package com.shopspark.ShopSpark.service.user;

import com.shopspark.ShopSpark.entity.user.person;
import com.shopspark.ShopSpark.exceptions.EmailAlreadyExistsException;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.repository.user.personrepository;
import com.shopspark.ShopSpark.repository.user.rolesrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@Slf4j
public class userservice {
    @Autowired
    personrepository personrepository;
    @Autowired
    rolesrepository rolesrepository;
    public ResponseEntity<person> registerUser(person person) throws EmailAlreadyExistsException, SomethingWentWrongException {
        String email = person.getEmail();
        System.out.println(person.toString());
        if(personrepository.findByEmail(email)!=null){
            log.error("This Email Already Exists, Please try with another Email.");
            throw new EmailAlreadyExistsException("This Email Already Exists, Please try with another Email.");
        }
        person.setRoles(rolesrepository.getByrolename("USER"));
        System.out.println(person.toString());
        try{
            personrepository.save(person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something Went Wrong. Try Again Later..");
        }
    }

}
