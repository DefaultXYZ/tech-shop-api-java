package com.techshop.controller;

import com.techshop.data.TechProduct;
import com.techshop.data.TechShop;
import com.techshop.dto.BuyProductResponse;
import com.techshop.dto.ResponseData;
import com.techshop.service.TechProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final TechProductService techProductService;

    ApiController(TechProductService techProductService) {
        this.techProductService = techProductService;
    }

    @GetMapping("/product/all")
    ResponseEntity<ResponseData<List<TechProduct>>> getAll(@RequestParam("page") Integer page) {
        List<TechProduct> products = techProductService.getAllByPageNumber(page);
        return ResponseEntity.ok(new ResponseData<>(products));
    }

    @GetMapping("/product")
    ResponseEntity<ResponseData<TechProduct>> getById(@RequestParam("id") Integer id) {
        try {
            TechProduct product = techProductService.getProductById(id);
            return ResponseEntity.ok(new ResponseData<>(product));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/available")
    ResponseEntity<ResponseData<List<TechShop>>> getAvailableShops(@RequestParam("id") Integer productId) {
        try {
            List<TechShop> techShops = techProductService.getAvailableShopsForProduct(productId);
            return ResponseEntity.ok(new ResponseData<>(techShops));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product/buy")
    ResponseEntity<ResponseData<BuyProductResponse>> buy(
        @RequestParam("productId") Integer productId,
        @RequestParam("shopId") Integer shopId,
        @RequestParam("amount") Integer amount
    ) {
        try {
            BuyProductResponse response = techProductService.performSellOperation(productId, shopId, amount);
            return ResponseEntity.ok(new ResponseData<>(response));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
