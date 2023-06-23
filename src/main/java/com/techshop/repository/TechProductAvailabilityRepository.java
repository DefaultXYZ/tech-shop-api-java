package com.techshop.repository;

import com.techshop.data.TechProductAvailability;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TechProductAvailabilityRepository extends JpaRepository<TechProductAvailability, Integer> {
    @Transactional
    @Modifying
    @Query("""
        update TechProductAvailability a
        set a.amount = greatest(a.amount - :amount, 0)
        where a.product.id = :productId and a.shop.id = :shopId and a.amount > 0
        """)
    void decreaseAmountFor(Integer productId, Integer shopId, Integer amount);

    @Transactional
    @Modifying
    @Query("""
        update TechProductAvailability a
        set a.amount = :amount
        where a.product.id = :productId and a.shop.id = :shopId
        """)
    void updateAmountFor(Integer productId, Integer shopId, Integer amount);

    @Query("""
        select a.amount
        from TechProductAvailability a
        where a.product.id = :productId and a.shop.id = :shopId
        """)
    Integer getAvailableAmount(Integer productId, Integer shopId);
}
