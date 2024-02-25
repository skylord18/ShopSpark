package com.shopspark.ShopSpark.controller.inventory;
import com.shopspark.ShopSpark.entity.inventory.listing;
import com.shopspark.ShopSpark.exceptions.InvalidInputFormat;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.service.inventory.listingservice;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;
import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("listing")
@Slf4j
public class listingcontroller {
    @Autowired
    listingservice listingservice;
    @GetMapping({"getall/{pageno}", "getall/page/{pageno}"})
    public ResponseEntity<Page<List<listing>>> getallistings(@PathVariable("pageno") Integer pageno) throws SomethingWentWrongException {
        return listingservice.getallistings(pageno);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<Optional<listing>> getlistingbyid(@PathVariable("id") Integer id) throws SomethingWentWrongException {
        return listingservice.getlistingbyid(id);
    }
    @PostMapping("add")
    public ResponseEntity<listing> addlisting(@Valid @RequestBody listing listing, Errors errors) throws InvalidInputFormat, SomethingWentWrongException {
        if(errors.hasErrors()){
            StringBuilder sb = new StringBuilder("The following Errors Occurred:");
            for(ObjectError obj : errors.getAllErrors()){log.error(obj.getDefaultMessage());sb.append(obj.getDefaultMessage());sb.append(System.getProperty("line.separator"));}
            throw new InvalidInputFormat(sb.toString());
        }
        return listingservice.addlisting(listing);
    }
    @GetMapping({"productid/{productid}/{pageno}", "productid/page/{productid}/{pageno}"})
    public ResponseEntity<Page<List<listing>>> fetchListingsByProductId(@PathVariable("productid") Integer productid, @PathVariable("pageno") Integer pageno) throws SomethingWentWrongException {
        return listingservice.fetchListingsByProductId(productid, pageno);
    }
    @GetMapping({"seller/{sellerName}/{pageno}", "seller/page/{sellerName}/{pageno}"} )
    public ResponseEntity<Page<List<listing>>> fetchlistingsbyseller(@PathVariable("sellerName") String sellerName, @PathVariable("pageno")Integer pageno) throws SomethingWentWrongException {
        return listingservice.fetchlistingsbysellerName(sellerName, pageno);
    }

}
