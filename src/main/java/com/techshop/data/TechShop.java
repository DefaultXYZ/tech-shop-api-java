package com.techshop.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "tech_shop")
@NoArgsConstructor
public class TechShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String address;

    @ManyToMany
    @JoinTable(
        name = "tech_product_availability",
        joinColumns = @JoinColumn(name = "shop_id")
    )
    private Set<TechProduct> products;
}
