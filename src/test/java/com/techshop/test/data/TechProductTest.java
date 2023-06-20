package com.techshop.test.data;

import com.techshop.data.TechProduct;
import com.techshop.data.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TechProductTest {

    @Test
    void shouldHaveData_whenSetter() {
        Type mocked = Mockito.mock();
        TechProduct given = new TechProduct();
        given.setId(1);
        given.setName("test");
        given.setType(mocked);

        assertEquals(1, given.getId());
        assertEquals("test", given.getName());
        assertEquals(mocked, given.getType());
    }

    @Test
    void shouldBeTrue_whenCompareSimilar() {
        TechProduct product1 = new TechProduct();
        product1.setId(1);
        product1.setName("test1");

        TechProduct product2 = new TechProduct();
        product2.setId(1);
        product2.setName("test1");

        assertEquals(product1, product2);
    }

    @Test
    void shouldBeTrue_whenCompareSimilarToString() {
        TechProduct product1 = new TechProduct();
        product1.setId(1);
        product1.setName("test1");

        TechProduct product2 = new TechProduct();
        product2.setId(1);
        product2.setName("test1");

        assertEquals(product1.toString(), product2.toString());
    }

    @Test
    void shouldBeTrue_whenCompareSimilarHashCode() {
        TechProduct product1 = new TechProduct();
        product1.setId(1);
        product1.setName("test1");

        TechProduct product2 = new TechProduct();
        product2.setId(1);
        product2.setName("test1");

        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void shouldBeFalse_whenCompareDifferent() {
        TechProduct product1 = new TechProduct();
        product1.setId(1);
        product1.setName("test1");

        TechProduct product2 = new TechProduct();
        product2.setId(2);
        product2.setName("test2");

        Assertions.assertNotEquals(product1, product2);
    }

    @Test
    void shouldBeFalse_whenCompareDifferentIds() {
        TechProduct product1 = new TechProduct();
        product1.setId(1);
        product1.setName("test1");

        TechProduct product2 = new TechProduct();
        product2.setId(2);
        product2.setName("test1");

        Assertions.assertNotEquals(product1, product2);
    }
}
