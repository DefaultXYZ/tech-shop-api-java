package com.techshop.dto;

import com.techshop.data.TechProduct;

public record BuyProductResponse(TechProduct product, Double totalCost, Integer amount) {
}
