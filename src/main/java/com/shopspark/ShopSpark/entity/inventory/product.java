package com.shopspark.ShopSpark.entity.inventory;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class product {
    private enum Category {
        Electronics, Clothing, Shoes, Jewelry, Home,
        Kitchen, Books, Beauty, PersonalCare, Health,
        Household, Toys, Games, Sports, Outdoors, Automotive,
        Tools, HomeImprovement, Grocery, GourmetFood, PetSupplies,
        Baby, OfficeProducts, TV, MusicalInstruments
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String brand;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = listing.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<listing> listings;
    @OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = feature.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<feature> features;

    public product(String brand, String name, Category category, List<listing> listings, List<feature> features) {
        this.brand = brand;
        this.name = name;
        this.category = category;
        this.listings = listings;
        this.features = features;
    }
}
