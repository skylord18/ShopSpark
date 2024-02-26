package com.shopspark.ShopSpark.entity.user;

import com.shopspark.ShopSpark.entity.baseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class address extends baseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

}
