package com.techshop.test.mocks;

public class MockData {
    public static final Integer TYPE_ID_11 = 11;
    public static final Integer TYPE_ID_12 = 12;
    public static final Integer PRODUCT_ID_51 = 51;
    public static final Integer SHOP_ID_41 = 41;
    public static final Integer PRODUCT_AVAILABILITY_ID_61 = 61;

    public static String getTechProductName(Integer id) {
        return String.format("Test %d", id % 50);
    }

    public static String getTechProductDescription(Integer id) {
        return String.format("Desc %d", id % 50);
    }

    public static Double getTechProductCost(Integer id) {
        return 20.1 + (id % 50);
    }

    public static String getTypeName(Integer id) {
        return String.format("Test Type %d", id % 10);
    }

    public static String getTechShopAddress(Integer id) {
        return String.format("Address %d", id % 40);
    }
}
