package com.shopspark.ShopSpark.entity.inventory;

import com.shopspark.ShopSpark.annotations.NoSpecialCharacters;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
//    Sample JSON
//{
//    "brand": "OnePlus",
//        "name": "OnePlus 24 in Full Hd LCD Monitor Led Backlit IPS Panel with 3-Side Bezel Less,USB Type-C Connectivity with 18W Charging,8 Mm Slim,Flicker Free Monitor (Response Time: 5 Ms,75 Hz Refresh Rate),Black",
//        "category": "Electronics",
//        "listings": [
//    {
//        "sellerName": "Suresh Electronics",
//            "price": 7800,
//            "avlQty": 3,
//            "isActive": true
//    },
//    {
//        "sellerName": "Ramesh Electronics",
//            "price": 7490,
//            "avlQty": 2,
//            "isActive": true
//    }
//    ],
//    "features": [
//    {
//        "property": "Screen Size",
//            "value": "24 inches"
//    },
//    {
//        "property": "Display Resolution",
//            "value": "1920 x 1080 Pixels"
//    },
//    {
//        "property": "Special Feature",
//            "value": "Flicker Free"
//    },
//    {
//        "property": "Refresh Rate",
//            "value": "75 Hz"
//    }
//    ]
//}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NotNull(message = "Brand Name Cannot be NULL")
    @NotEmpty(message = "Brand Name Cannot be Empty")
    @NotBlank(message = "Brand Name Cannot be Blank")
    @Size(min = 2, max = 200, message = "Brand Name Must be Between 2 to 200 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Brand Name Must Contain Only Alphanumeric Characters")
    private String brand;
    @NotNull(message = "Name Cannot be NULL")
    @NotEmpty(message = "Name Cannot be Empty")
    @NotBlank(message = "Name Cannot be Blank")
    @Size(min = 2, max = 200, message = "Name Must be Between 2 to 200 characters.")
    private String name;

    @NotNull(message = "Category Cannot be NULL")
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
