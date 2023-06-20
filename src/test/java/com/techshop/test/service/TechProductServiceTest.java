package com.techshop.test.service;

import com.techshop.data.TechProduct;
import com.techshop.data.Type;
import com.techshop.dto.CreateTechProduct;
import com.techshop.repository.TechProductRepository;
import com.techshop.repository.TypeRepository;
import com.techshop.service.TechProductService;
import com.techshop.test.annotation.ServiceTest;
import com.techshop.test.mocks.MockData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ServiceTest
public class TechProductServiceTest {

    @InjectMocks
    private TechProductService service;

    @Mock
    private TechProductRepository repository;

    @Mock
    private TypeRepository typeRepository;

    @Test
    void shouldSaveProduct_whenCreateNew() {
        CreateTechProduct given = MockData.createTechProduct();
        Type type = new Type();
        type.setId(MockData.Type.ID);
        TechProduct expected = new TechProduct();
        expected.setName(MockData.TechProduct.NAME);
        expected.setDescription(MockData.TechProduct.DESCRIPTION);
        expected.setCost(MockData.TechProduct.COST);
        expected.setType(type);
        when(typeRepository.getReferenceById(eq(MockData.Type.ID))).thenReturn(type);
        when(repository.saveAndFlush(any())).thenReturn(expected);

        TechProduct actual = service.createNew(given);

        verify(repository).saveAndFlush(eq(expected));
        Assertions.assertEquals(expected, actual);
    }
}
