package com.techshop.service;

import com.techshop.data.TechProduct;
import com.techshop.data.Type;
import com.techshop.dto.CreateTechProduct;
import com.techshop.repository.TechProductRepository;
import com.techshop.repository.TypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TechProductService {
    private final TypeRepository typeRepository;
    private final TechProductRepository repository;

    public TechProductService(TypeRepository typeRepository, TechProductRepository repository) {
        this.typeRepository = typeRepository;
        this.repository = repository;
    }

    public TechProduct createNew(CreateTechProduct createTechProduct) {
        Type type = typeRepository.getReferenceById(createTechProduct.typeId());
        TechProduct product = new TechProduct();
        product.setName(createTechProduct.name());
        product.setType(type);
        product.setDescription(createTechProduct.description());
        product.setCost(createTechProduct.cost());
        return repository.saveAndFlush(product);
    }
}
