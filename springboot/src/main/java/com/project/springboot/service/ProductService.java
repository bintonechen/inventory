/**
 * Author: Bintong
 * Date: 2024/7/8 10:33
 */

package com.project.springboot.service;

import com.project.springboot.entity.Customer;
import com.project.springboot.entity.Product;
import com.project.springboot.exception.ServiceException;
import com.project.springboot.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public void insertProduct(Product product) {
        productMapper.insert(product);
    }

    public void updateProduct(Product product){
        productMapper.update(product);
    }

    public void deleteProduct(String barcode){
        productMapper.delete(barcode);
    }

    public void batchDeleteProduct(List<String> barcodes) {
        for (String barcode : barcodes){
            productMapper.delete(barcode);
        }
    }

    public List<Product> selectAll() {
        return productMapper.selectAll();
    }

    public Product addProduct(Product product) {
        // to check if the product barcode already exists
        Product databaseProductBarcode = productMapper.selectByProductBarcode(product.getProductBarcode());
        if(databaseProductBarcode != null){
            throw new ServiceException("Product Barcode already exists. ");
        }
        productMapper.insert(product);
        return null;
    }

    public Product getProductByBarcode(String barcode) {
        return productMapper.selectByProductBarcode(barcode);
    }
}
