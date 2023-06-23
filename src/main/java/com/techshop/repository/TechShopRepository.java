package com.techshop.repository;

import com.techshop.data.TechShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechShopRepository extends JpaRepository<TechShop, Integer> {
}
