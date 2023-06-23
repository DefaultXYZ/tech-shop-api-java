package com.techshop.controller;

import com.techshop.data.TechProduct;
import com.techshop.dto.CreateTechProduct;
import com.techshop.dto.ResponseData;
import com.techshop.service.TechProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("admin")
@RestController
@RequestMapping("/api")
public class AdminApiController {
    private final TechProductService techProductService;

    public AdminApiController(TechProductService techProductService) {
        this.techProductService = techProductService;
    }

    @PutMapping("/product")
    ResponseEntity<ResponseData<TechProduct>> putNew(@RequestBody CreateTechProduct product) {
        TechProduct result = techProductService.createNew(product);
        ResponseData<TechProduct> data = new ResponseData<>(result);
        return ResponseEntity.ok(data);
    }
}
