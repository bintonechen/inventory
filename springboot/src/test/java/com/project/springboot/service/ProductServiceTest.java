/**
 * Author: Bintong
 * Date: 2024/7/29
 */

package com.project.springboot.service;

import com.project.springboot.entity.Product;
import com.project.springboot.exception.ServiceException;
import com.project.springboot.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setProductBarcode("123456");
        product.setProductName("Test Product");

        when(productMapper.selectByProductBarcode("123456")).thenReturn(null);

        productService.addProduct(product);

        verify(productMapper, times(1)).insert(product);
    }

    @Test
    public void testAddProductWithExistingBarcode() {
        Product product = new Product();
        product.setProductBarcode("123456");
        product.setProductName("Test Product");

        when(productMapper.selectByProductBarcode("123456")).thenReturn(product);

        assertThrows(ServiceException.class, () -> productService.addProduct(product));
        verify(productMapper, never()).insert(any(Product.class));
    }

    @Test
    public void testGetProductByBarcode() {
        Product product = new Product();
        product.setProductBarcode("123456");
        product.setProductName("Test Product");

        when(productMapper.selectByProductBarcode("123456")).thenReturn(product);

        Product result = productService.getProductByBarcode("123456");

        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct("123456");
        verify(productMapper, times(1)).delete("123456");
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setProductBarcode("123456");
        product.setProductName("Updated Product");

        productService.updateProduct(product);
        verify(productMapper, times(1)).update(product);
    }

    @Test
    public void testSelectAll() {
        productService.selectAll();
        verify(productMapper, times(1)).selectAll();
    }
}
