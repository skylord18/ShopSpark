package com.shopspark.ShopSpark.service.order;
import com.shopspark.ShopSpark.entity.inventory.listing;
import com.shopspark.ShopSpark.entity.order.order;
import com.shopspark.ShopSpark.exceptions.SomethingWentWrongException;
import com.shopspark.ShopSpark.repository.inventory.listingrepository;
import com.shopspark.ShopSpark.repository.order.orderrepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static com.shopspark.ShopSpark.entity.order.order.orderStatus.Received;

@Service
public class orderservice {
    final Object o = new Object();
    @Autowired
    listingrepository listingrepository;
    @Autowired
    orderrepository orderrepository;
    @Transactional
    public ResponseEntity<order> placeorder(order order, Authentication authentication) throws Exception {
        Integer product_id = order.getProductId(), listing_id = order.getListingId();
        Integer productIdFromDb = listingrepository.getProdId(listing_id);
        if(!product_id.equals(productIdFromDb))throw new SomethingWentWrongException("Product ID's Don't Match.");
        //if(!authentication.getAuthorities().contains("ROLE_USER"))throw new Exception("Cannot Place Order,As you dont't have authority to do so.");
        Optional<listing> op = listingrepository.findById(listing_id);
        listing targetListing = op.orElse(null);
        if(targetListing == null)throw new Exception("Invalid Listing ID");
        if(targetListing.getAvlQty() < order.getQuantity())throw new Exception("Insufficient Available Quantity, Try with Lower Quantity, Available Quantity is " + targetListing.getAvlQty());
        targetListing.setAvlQty(targetListing.getAvlQty() - order.getQuantity());
        order.setOrderStatus(Received);
        if(targetListing.getPrice() * order.getQuantity() <500)order.setShipping(40);else order.setShipping(0);
        order.setGrandTotal(order.getQuantity() * targetListing.getPrice() + order.getShipping());
        order.setSellerDetails(targetListing.getCreatedBy());
        order.setCreatedBy(authentication.getPrincipal().toString());
        order.setUpdatedBy(authentication.getPrincipal().toString());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        try{
                listingrepository.save(targetListing);
                orderrepository.save(order);
                return new ResponseEntity<>(order, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<order>> getmyoders(Authentication authentication) {
        try{
            return new ResponseEntity<>(orderrepository.findByCreatedBy(authentication.getPrincipal().toString()), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<order>> getordersasseller(Authentication authentication) {
        try{
            return new ResponseEntity<>(orderrepository.findBySellerDetails(authentication.getPrincipal().toString()),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<order> updateorderstatus(Authentication authentication, Integer id, String orderstatus) throws Exception {
        order op = orderrepository.findById(id).orElse(null);
        if(op==null)throw new Exception("Invalid Order");
        if(authentication.getPrincipal().toString().equals(op.getSellerDetails())){
            orderrepository.updateorderStatus(id, orderstatus);
            return new ResponseEntity<>(orderrepository.findById(id).orElse(null),HttpStatus.OK);
        }else{
            throw new Exception("Unauthorized Operation.");
        }
    }
}
