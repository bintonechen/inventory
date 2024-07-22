/**
 * Author: Bintong
 * Date: 2024/7/8 10:31
 */

package com.project.springboot.controller;

import com.project.springboot.common.Result;
import com.project.springboot.entity.Customer;
import com.project.springboot.entity.Product;
import com.project.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     *  Add a new product
     */
    @PostMapping("/add")
    public Result add(@RequestBody Product product){
        try {
            productService.insertProduct(product);
        } catch(Exception e){
            if(e instanceof SQLException){
                return Result.error("insert into database error");
            } else {
                return Result.error("system error");
            }
        }
        return Result.success();
    }

    /**
     *  add a new product
     */
    @PostMapping("/addproduct")
    public Result registry(@RequestBody Product product){
        product =productService.addProduct(product);
        return Result.success(product);
    }

    /**
     *  update an existing product
     */
    @PutMapping("/update")
    public Result update(@RequestBody Product product){
        try {
            productService.updateProduct(product);
        } catch(Exception e){
//            if(e instanceof SQLException){
//                return Result.error("insert into database error");
//            } else {
//                return Result.error("system error");
//            }
        }
        return Result.success(product);
    }

    /**
     *  delete an existing product
     */
    @DeleteMapping("/delete/{barcode}")
    public Result delete(@PathVariable String barcode){
        productService.deleteProduct(barcode);
        return Result.success();
    }

    /**
     *  delete several existing products
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<String> barcodes){
        productService.batchDeleteProduct(barcodes);
        return Result.success();
    }

    /**
     *  display all products
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<Product> productList = productService.selectAll();
        return Result.success(productList);
    }

    /**
     * Get a product by its barcode
     */
    @GetMapping("/{barcode}")
    public Result getProductByBarcode(@PathVariable String barcode){
        Product product = productService.getProductByBarcode(barcode);
        if (product != null) {
            return Result.success(product);
        } else {
            return Result.error("Product not found");
        }
    }

}
