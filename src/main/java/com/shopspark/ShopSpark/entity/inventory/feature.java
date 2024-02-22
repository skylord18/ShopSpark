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
public class feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = product.class, fetch = FetchType.EAGER)
    private product product;
    private String property;
    private String value;

    public feature(String property, String value) {
        this.property = property;
        this.value = value;
    }
}
