package com.techshop.test.repository;

import com.techshop.repository.TechProductAvailabilityRepository;
import com.techshop.test.annotation.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RepositoryTest
public class TechProductAvailabilityRepositoryTest {

    @Autowired
    private TechProductAvailabilityRepository repository;

    @BeforeEach
    void setUp() {
        repository.updateAmountFor(51, 41, 10);
    }

    @Test
    void shouldReturnAvailableAmount() {
        Integer actual = repository.getAvailableAmount(51, 41);
        assertEquals(10, actual);
    }

    @Test
    void shouldUpdateAmount() {
        Integer current = repository.findById(61).orElseThrow().getAmount();
        assertEquals(10, current);

        repository.updateAmountFor(51, 41, 15);
        Integer actual = repository.findById(61).orElseThrow().getAmount();

        assertEquals(15, actual);
    }

    @Test
    void shouldDecrease_whenProductAndShopFound() {
        repository.decreaseAmountFor(51, 41, 1);
        Integer actual = repository.findById(61).orElseThrow().getAmount();

        assertEquals(9, actual);
    }

    @Test
    void shouldNotDecrease_whenAmountIsZero() {
        repository.decreaseAmountFor(51, 41, 6);
        Integer actual1 = repository.findById(61).orElseThrow().getAmount();
        assertEquals(4, actual1);

        repository.decreaseAmountFor(51, 41, 4);
        Integer actual2 = repository.findById(61).orElseThrow().getAmount();
        assertEquals(0, actual2);

        repository.decreaseAmountFor(51, 41, 5);
        Integer actual3 = repository.findById(61).orElseThrow().getAmount();
        assertEquals(0, actual3);
    }
}
