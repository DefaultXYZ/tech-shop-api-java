package com.techshop.test.mocks;

import com.techshop.dto.CreateTechProduct;

public class MockData {
    public static class TechProduct {
        public static final Integer ID = 1;
        public static final String NAME = "test-product";
        public static final String DESCRIPTION = "Test description";
        public static final Double COST = 2.4;
    }

    public static class Type {
        public static final Integer ID = 1;
        public static final String NAME = "Test type";
    }

    public static CreateTechProduct createTechProduct() {
        return new CreateTechProduct(TechProduct.NAME, Type.ID, TechProduct.DESCRIPTION, TechProduct.COST);
    }
}
