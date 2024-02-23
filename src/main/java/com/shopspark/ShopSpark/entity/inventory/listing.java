package com.shopspark.ShopSpark.entity.inventory;

import jakarta.persistence.*;
import lombok.*;
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
    private product product;
    @Column(name = "seller_name")
    private String sellerName;
    private Integer price;
    @Column(name = "avl_qty")
    private Integer avlQty;
    @Column(name = "is_active")
    private Boolean isActive;

}
