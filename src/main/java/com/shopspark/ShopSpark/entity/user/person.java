package com.shopspark.ShopSpark.entity.user;

import com.shopspark.ShopSpark.entity.baseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class person extends baseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name Cannot be Blank")
    @Size(min = 3, max = 128, message = "Name must contain between 3 to 128 characters")
    private String name;
    @NotBlank(message = "Mobile Number Cannot be Blank")
    @Pattern(regexp = "(ˆ$|[0-9]{10})", message = "Phone Number must contain 10 digits")
    private String mobile;
    @NotBlank(message = "Email Address Cannot be Blank")
    @Email(message = "Please Provide a valid Email Address")
    private String email;
    @NotBlank(message = "Password Cannot be Blank")
    @Size(min = 8, message = "Password Cannot be less than 7 characters")
    private String password;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private roles roles;
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = address.class)
//    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
//    private address address;
}
