package com.shopspark.ShopSpark.entity.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopspark.ShopSpark.annotations.NoSpecialCharacters;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JsonIgnore
    private product product;
    @NotNull(message = "Seller Name Cannot be NULL")
    @NotEmpty(message = "Seller Name Cannot be Empty")
    @NotBlank(message = "Seller Name Cannot be Blank")
    @Size(min = 2, max = 200, message = "Seller Name Must be Between 2 to 200 characters.")
    @Column(name = "seller_name")
    private String sellerName;
    @Min(value = 10,message = "Minimum Price is 10")
    @Max(value = 100000,message = "Minimum Price is 100000")
    private Integer price;
    @Min(value = 1, message = "Minimum Quantity Allowed is 1")
    @Max(value = 1000, message = "Maximum Quantity Allowed is 1000")
    @Column(name = "avl_qty")
    private Integer avlQty;
    @Column(name = "is_active")
    private Boolean isActive;

}
