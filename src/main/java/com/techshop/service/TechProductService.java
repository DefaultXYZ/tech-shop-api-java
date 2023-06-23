package com.techshop.service;

import com.techshop.data.TechProduct;
import com.techshop.data.TechShop;
import com.techshop.data.Type;
import com.techshop.dto.BuyProductResponse;
import com.techshop.dto.CreateTechProduct;
import com.techshop.repository.TechProductAvailabilityRepository;
import com.techshop.repository.TechProductRepository;
import com.techshop.repository.TypeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TechProductService {
    private final TypeRepository typeRepository;
    private final TechProductRepository productRepository;
    private final TechProductAvailabilityRepository productAvailabilityRepository;

    public TechProductService(
        TypeRepository typeRepository,
        TechProductRepository productRepository,
        TechProductAvailabilityRepository productAvailabilityRepository
    ) {
        this.typeRepository = typeRepository;
        this.productRepository = productRepository;
        this.productAvailabilityRepository = productAvailabilityRepository;
    }

    public List<TechProduct> getAllByPageNumber(Integer page) {
        Sort sort = Pageable.ofSize(5)
            .withPage(page)
            .getSortOr(
                Sort.sort(TechProduct.class)
                    .by(TechProduct::getName)
                    .ascending()
            );
        return productRepository.findAll(sort);
    }

    public TechProduct getProductById(Integer id) throws NoSuchElementException {
        return productRepository.findById(id).orElseThrow();
    }

    public List<TechShop> getAvailableShopsForProduct(Integer productId) throws NoSuchElementException {
        TechProduct techProduct = productRepository.findById(productId).orElseThrow();
        return techProduct.getShops().stream().toList();
    }

    @Transactional
    public BuyProductResponse performSellOperation(
        Integer productId,
        Integer shopId,
        Integer amount
    ) throws NoSuchElementException {
        TechProduct product = productRepository.findById(productId).orElseThrow();
        double totalCost = product.getCost() * amount;
        productAvailabilityRepository.decreaseAmountFor(productId, shopId, 1);
        return new BuyProductResponse(product, totalCost, amount);
    }

    public TechProduct createNew(CreateTechProduct createTechProduct) {
        Type type = typeRepository.getReferenceById(createTechProduct.typeId());
        TechProduct product = new TechProduct();
        product.setName(createTechProduct.name());
        product.setType(type);
        product.setDescription(createTechProduct.description());
        product.setCost(createTechProduct.cost());
        return productRepository.saveAndFlush(product);
    }
}
