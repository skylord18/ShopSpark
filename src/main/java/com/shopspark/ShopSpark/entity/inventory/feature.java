package com.shopspark.ShopSpark.entity.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopspark.ShopSpark.annotations.NoSpecialCharacters;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class feature extends baseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, targetEntity = product.class, fetch = FetchType.EAGER)
    @JsonIgnore
    private product product;
    @NotNull(message = "Property Cannot be NULL")
    @NotEmpty(message = "Property Cannot be Empty")
    @NotBlank(message = "Property Cannot be Blank")
    @Size(min = 2, max = 200, message = "Property Name Must be Between 2 to 200 characters.")
    private String property;
    @NotNull(message = "Value Cannot be NULL")
    @NotEmpty(message = "Value Cannot be Empty")
    @NotBlank(message = "Value Cannot be Blank")
    @Size(min = 1, max = 200, message = "Value Must be Between 1 to 200 characters.")
    private String value;

    public feature(String property, String value) {
        this.property = property;
        this.value = value;
    }
}
