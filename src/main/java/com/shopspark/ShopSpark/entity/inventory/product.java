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
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = listing.class, fetch = FetchType.EAGER)
    private List<listing> listings;
    @OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = feature.class, fetch = FetchType.EAGER)
    private List<feature> features;
    @ManyToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = brand.class, fetch = FetchType.EAGER)
    private brand brand;

    public product(Category category, List<listing> listings, List<feature> features, brand brand) {
        this.category = category;
        this.listings = listings;
        this.features = features;
        this.brand = brand;
    }
}
