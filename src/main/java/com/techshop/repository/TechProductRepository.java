package com.techshop.repository;

import com.techshop.data.TechProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechProductRepository extends JpaRepository<TechProduct, Integer> {
}
