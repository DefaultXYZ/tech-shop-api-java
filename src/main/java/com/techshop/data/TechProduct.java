package com.techshop.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Table(name = "tech_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double cost;

    @ManyToMany
    @JoinTable(
        name = "tech_product_availability",
        joinColumns = @JoinColumn(name = "product_id")
    )
    private Set<TechShop> shops;
}
