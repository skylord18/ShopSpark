package com.shopspark.ShopSpark.entity.order;

import com.shopspark.ShopSpark.entity.baseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "EcommerceOrder")
public class order extends baseEntity {
    public enum orderStatus{
        Received, Processing, Shipped, OutForDelivery, Delivered
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;

    private Integer listingId;

    @Min(value = 1, message = "Min Quantity Allowed is 1")
    @Max(value = 10, message = "Max Allowed Quantity is 10")
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    @Null(message = "orderStatus must be NULL")
    private orderStatus orderStatus;
    @Null(message = "shipping must be NULL")
    private Integer shipping;
    @Null(message = "grandTotal Must be NULL")
    private Integer grandTotal;

    @NotBlank(message = "Address 1 Cannot be Blank")
    @Size(min = 5, message = "Address 1 Cannot be less than 5 characters")
    private String address1;
    @NotBlank(message = "Address 2 Cannot be Blank")
    @Size(min = 5, message = "Address 2 Cannot be less than 5 characters")
    private String address2;
    @NotBlank(message = "City Cannot be Blank")
    @Size(min = 5, message = "City Cannot be less than 5 characters")
    private String city;
    @NotBlank(message = "State Cannot be Blank")
    @Size(min = 5, message = "State Cannot be less than 5 characters")
    private String state;
    @NotBlank(message = "Zipcode Cannot be Blank")
    @Size(min = 5, message = "Zipcode Cannot be less than 5 Digits")
    @Pattern(regexp = "(ˆ$|[0-9]{5})", message = "Zipcode Cannot be less than 5 Digits")
    private String zipcode;
    @Null(message = "sellerDetails Must be NULL")
    private String sellerDetails;

}
