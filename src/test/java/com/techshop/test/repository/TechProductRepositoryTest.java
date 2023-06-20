package com.techshop.test.repository;

import com.techshop.data.TechProduct;
import com.techshop.data.Type;
import com.techshop.repository.TechProductRepository;
import com.techshop.repository.TypeRepository;
import com.techshop.test.annotation.RepositoryTest;
import com.techshop.test.mocks.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RepositoryTest
public class TechProductRepositoryTest {

    @Autowired
    private TechProductRepository repository;

    @Autowired
    private TypeRepository typeRepository;

    @Test
    void shouldHasItem_whenSave() {
        Type typeRef = typeRepository.getReferenceById(MockData.Type.ID);
        TechProduct given = new TechProduct();
        given.setName(MockData.TechProduct.NAME);
        given.setDescription(MockData.TechProduct.DESCRIPTION);
        given.setCost(MockData.TechProduct.COST);
        given.setType(typeRef);
        Optional<Type> type = typeRepository.findById(MockData.Type.ID);

        repository.deleteAll();
        repository.saveAndFlush(given);
        Optional<TechProduct> actualOptional = repository.findById(MockData.TechProduct.ID);
        Assertions.assertTrue(actualOptional.isPresent());
        TechProduct actual = actualOptional.get();

        Assertions.assertEquals(MockData.TechProduct.ID, actual.getId());
        Assertions.assertEquals(MockData.TechProduct.NAME, actual.getName());
        Assertions.assertTrue(type.isPresent());
        Assertions.assertEquals(type.get(), actual.getType());
    }
}
