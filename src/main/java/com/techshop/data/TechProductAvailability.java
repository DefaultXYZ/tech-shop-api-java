package com.techshop.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tech_product_availability")
@NoArgsConstructor
public class TechProductAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private TechProduct product;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private TechShop shop;

    @Column(nullable = false)
    private Integer amount;
}
