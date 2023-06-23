package com.techshop.test.service;

import com.techshop.data.TechProduct;
import com.techshop.data.Type;
import com.techshop.dto.CreateTechProduct;
import com.techshop.repository.TechProductRepository;
import com.techshop.repository.TypeRepository;
import com.techshop.service.TechProductService;
import com.techshop.test.annotation.ServiceTest;
import com.techshop.test.mocks.MockData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ServiceTest
public class TechProductServiceTest {

    @InjectMocks
    private TechProductService service;

    @Mock
    private TechProductRepository productRepository;

    @Mock
    private TypeRepository typeRepository;

    @Test
    void shouldSaveProduct_whenCreateNew() {
        Type type = mock();
        CreateTechProduct given = new CreateTechProduct("Name", MockData.TYPE_ID_11, "Desc", 1.1);
        when(typeRepository.getReferenceById(eq(MockData.TYPE_ID_11))).thenReturn(type);

        service.createNew(given);

        TechProduct expected = TechProduct.builder().name("Name").description("Desc").cost(1.1).type(type).build();
        verify(productRepository, times(1)).saveAndFlush(eq(expected));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void shouldGetProduct_whenFindById() {
        when(productRepository.findById(eq(MockData.PRODUCT_ID_51))).thenReturn(Optional.of(mock()));

        service.getProductById(MockData.PRODUCT_ID_51);

        verify(productRepository, times(1)).findById(MockData.PRODUCT_ID_51);
        verifyNoMoreInteractions(productRepository);
    }
}
